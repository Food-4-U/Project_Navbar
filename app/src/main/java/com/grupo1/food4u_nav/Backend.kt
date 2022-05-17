import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.grupo1.food4u_nav.models.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import java.io.InputStream
import org.json.JSONArray as JSONArray
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject as JSONObject1


object Backend {

    const val BASE_API = "https://localhost:7273/"

    fun getAllClientes( callback : (( List<Cliente>)->Unit) ) {
        var clientes = arrayListOf<Cliente>()
        GlobalScope.launch (Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente")
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultArray = JSONArray(result)

                for (index in 0 until resultArray.length()){
                    var clienteJSON  = resultArray[index] as JSONObject1
                    var cliente = Cliente.fromJSON(clienteJSON)
                    clientes.add(cliente)
                }

                GlobalScope.launch (Dispatchers.Main){
                    callback.invoke(clientes)
                }
            }
        }
    }

    fun getClientes( id_cliente: Int, callback : (( Cliente)->Unit) ) {

        GlobalScope.launch (Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject1(result)
                var cliente = Cliente.fromJSON(resultJSONObject)

                GlobalScope.launch (Dispatchers.Main){
                    callback.invoke(cliente)
                }
            }
        }
    }
    fun addCliente( cliente: Cliente, callback : (( Boolean)->Unit) ) {

        GlobalScope.launch (Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, cliente.toJSON().toString())

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/Registar" )
                .post(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject1(result)

                GlobalScope.launch (Dispatchers.Main){
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    fun updateCliente(id_cliente: Int, cliente: Cliente, callback : (( Boolean)->Unit) ) {

        GlobalScope.launch (Dispatchers.IO) {
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body: RequestBody = RequestBody.create(
                mediaType, cliente.toJSON().toString())

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente )
                .put(body)
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject1(result)

                GlobalScope.launch (Dispatchers.Main){
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }

    fun deleteCliente(id_cliente: Int, callback : (( Boolean)->Unit) ) {

        GlobalScope.launch (Dispatchers.IO) {

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Cliente" + "/" + id_cliente )
                .delete()
                .build()
            client.newCall(request).execute().use { response ->
                var result = response.body!!.string()
                var resultJSONObject = JSONObject1(result)

                GlobalScope.launch (Dispatchers.Main){
                    val status = resultJSONObject.getString("status")
                    callback.invoke(status == "ok")
                }
            }
        }
    }


    fun getImage(  urlImage: String,  callback : ((Bitmap)->Unit) ){
        GlobalScope.launch (Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlImage)
                .build()
            client.newCall(request).execute().use { response ->
                response.body?.let { body->
                    val inputStream: InputStream? = body.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    GlobalScope.launch (Dispatchers.Main) {
                        callback.invoke(bitmap)
                    }
                }
            }
        }
    }
}