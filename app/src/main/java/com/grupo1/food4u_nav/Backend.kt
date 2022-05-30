import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.grupo1.food4u_nav.models.Cliente
import com.grupo1.food4u_nav.models.Item_Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.InputStream
import org.json.JSONArray as JSONArray
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody



object Backend {

    const val BASE_API = "http://18.130.229.13:5000/api/"

    fun getAllClientes(callback: ((List<Cliente>) -> Unit)) {
        var clientes = arrayListOf<Cliente>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var clienteJSON = resultArray[index] as JSONObject
                    var cliente = Cliente.fromJSON(clienteJSON)
                    clientes.add(cliente)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(clientes)
                }
            }
        }
    }

    fun getClienteEmail(email: String, callback: ((Cliente) -> Unit)) {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente/Get/" + email)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)
                var cliente = Cliente.fromJSON(resultJSONObject)

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(cliente)
                }
            }
        }
    }

    fun getClientes(id_cliente: Int, callback: ((Cliente) -> Unit)) {

        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)
                var cliente = Cliente.fromJSON(resultJSONObject)

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(cliente)
                }
            }
        }
    }

    fun addCliente(cliente: Cliente, callback: ((Boolean) -> Unit)) {

        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, cliente.toJSON().toString()
            )

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/Registar")
                .post(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)

                GlobalScope.launch(Dispatchers.Main) {
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    fun Login(cliente: Cliente, callback: (Boolean) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, cliente.toJSON().toString()
            )

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/Login")
                .post(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)

                GlobalScope.launch(Dispatchers.Main) {
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    fun updateCliente(id_cliente: Int, cliente: Cliente, callback: ((Boolean) -> Unit)) {

        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, cliente.toJSON().toString()
            )

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente)
                .put(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)

                GlobalScope.launch(Dispatchers.Main) {
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    fun deleteCliente(id_cliente: Int, callback: ((Boolean) -> Unit)) {

        GlobalScope.launch(Dispatchers.IO) {

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente)
                .delete()
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)

                GlobalScope.launch(Dispatchers.Main) {
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    // ITENS

    fun getItemSubCategory(subcategory: Int, callback: ((List<Item_Menu>) -> Unit)) {
        var itens = arrayListOf<Item_Menu>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item/ItemSubcategoria/" + subcategory)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var itemJSON = resultArray[index] as JSONObject
                    var item = Item_Menu.fromJSON(itemJSON)
                    itens.add(item)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(itens)
                }
            }
        }
    }

    fun getItemCategory(id_category: Int, callback: ((List<Item_Menu>) -> Unit)) {
        var itens = arrayListOf<Item_Menu>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item/ItemCategoria/" + id_category)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var itemJSON = resultArray[index] as JSONObject
                    var item = Item_Menu.fromJSON(itemJSON)
                    itens.add(item)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(itens)
                }
            }
        }
    }

    fun getItemTop(callback: ((List<Item_Menu>) -> Unit)) {
        var itens = arrayListOf<Item_Menu>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item/ItemHot")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var itemJSON = resultArray[index] as JSONObject
                    var item = Item_Menu.fromJSON(itemJSON)
                    itens.add(item)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(itens)
                }
            }
        }
    }

    fun getItemID(id: Int, callback: ((Item_Menu) -> Unit)) {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item/Get/" + id)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)
                var item = Item_Menu.fromJSON(resultJSONObject)

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(item)
                }
            }
        }
    }

    fun getImage(urlImage: String, callback: ((Bitmap) -> Unit)) {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlImage)
                .build()
            client.newCall(request).execute().use { response ->
                response.body?.let { body ->
                    val inputStream: InputStream? = body.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    GlobalScope.launch(Dispatchers.Main) {
                        callback.invoke(bitmap)
                    }
                }
            }
        }
    }
}