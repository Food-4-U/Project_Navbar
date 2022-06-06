import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Cliente
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
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

    fun getAllItens(callback: (List<Item_Menu>) -> Unit) {
        var itens = arrayListOf<Item_Menu>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item")
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

    fun addItem(item: Item_Menu, callback: (Boolean) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, item.toJSON().toString()
            )

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item" + "/AdicionarItem")
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

    fun updateItem(id: Int, item: Item_Menu, callback : ((Boolean)->Unit) ) {

        GlobalScope.launch (Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                    mediaType, item.toJSON().toString())
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Item/Update/" + id)
                .put(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)

                GlobalScope.launch (Dispatchers.Main){
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    // CATEGORIAS E SUBCATEGORIAS NOME

    fun getNameCategory(id: Int, callback: ((CategoryType) -> Unit)) {
        GlobalScope.launch(Dispatchers.IO) {
            var category = CategoryType
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Categoria/Get/" + id)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)
                var category = CategoryType.fromJSON(resultJSONObject)

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(category)
                }
            }
        }
    }

    fun getIDCategory(name: String, callback: (Int) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            var categoryID = 0
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Categoria/GetIDCategory/" + name)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                categoryID = result.toInt()

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(categoryID)
                }
            }
        }
    }

    fun getAllCategoryNames(callback: (List<String>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            var categories = arrayListOf<String>()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Categoria/GetNome")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)
                for (index in 0 until resultArray.length()) {
                    var categoryJSON = resultArray[index]
                    var categoryName = categoryJSON
                    categories.add(categoryName.toString())
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(categories)
                }
            }
        }
    }

    fun getAllCategories(callback: ((List<CategoryType>) -> Unit)) {
        var categories = arrayListOf<CategoryType>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Categoria")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var clienteJSON = resultArray[index] as JSONObject
                    var category = CategoryType.fromJSON(clienteJSON)
                    categories.add(category)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(categories)
                }
            }
        }
    }

    fun getNameSubcategory(id: Int, callback: ((SubCategories) -> Unit)) {
        GlobalScope.launch(Dispatchers.IO) {
            var subcategory = SubCategories
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Subcategoria/GetItem/" + id)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject(result)
                var subcategory = SubCategories.fromJSON(resultJSONObject)

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(subcategory)
                }
            }
        }
    }

    fun getAllSubcategories(callback: ((List<SubCategories>) -> Unit)) {
        var subcategories = arrayListOf<SubCategories>()
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Subcategoria")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()) {
                    var clienteJSON = resultArray[index] as JSONObject
                    var subcategory = SubCategories.fromJSON(clienteJSON)
                    subcategories.add(subcategory)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(subcategories)
                }
            }
        }
    }

    fun getAllSubcategoryNames(callback: (List<String>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            var subcategories = arrayListOf<String>()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Subcategoria/GetNome")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)
                for (index in 0 until resultArray.length()) {
                    var subcategoryJSON = resultArray[index]
                    var subcategoryName = subcategoryJSON
                    subcategories.add(subcategoryName.toString())
                }

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(subcategories)
                }
            }
        }
    }

    fun getIDSubcategory(name: String, callback: (Int) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            var subcategoryID: Int
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Subcategoria/GetIDSubcategory/" + name)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                subcategoryID = result.toInt()

                GlobalScope.launch(Dispatchers.Main) {
                    callback.invoke(subcategoryID)
                }
            }
        }
    }

    /*

                                        FUNCIONARIO
     */
}
