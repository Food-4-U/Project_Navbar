package com.grupo1.food4u_nav.models

import org.json.JSONObject

class Cliente {
    var id_cliente : Int? = null
    var nome : String? = null
    var email : String? = null
    var password : String? = null
    var nif: String? = null
    var genero: String? = null
    var idade : Int? = null
    var localidade : String? = null
    var concelho : String? = null
    var isAdmin : Boolean = false

    constructor(
        id_cliente : Int?,
        nome : String?,
        email : String?,
        password : String?,
        nif : String?,
        genero : String?,
        idade : Int?,
        localidade : String?,
        concelho : String?,
        isAdmin : Boolean
    ) {
        this.id_cliente = id_cliente
        this.nome = nome
        this.email = email
        this.password = password
        this.nif = nif
        this.genero = genero
        this.idade = idade
        this.localidade = localidade
        this.concelho = concelho
        this.isAdmin = isAdmin
    }

    fun toJSON() : JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("id_cliente", id_cliente)
        jsonObject.put("nome", nome)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        jsonObject.put("nif", nif)
        jsonObject.put("genero", genero)
        jsonObject.put("idade", idade)
        jsonObject.put("localidade", localidade)
        jsonObject.put("concelho", concelho)
        jsonObject.put("isAdmin", isAdmin)
        return jsonObject
    }

    //
    companion object{
        fun fromJSON(jsonObject: JSONObject): Cliente{
            return Cliente(
                jsonObject["id_cliente"] as? Int?,
                jsonObject["nome"] as? String?,
                jsonObject["email"] as? String?,
                jsonObject["password"] as? String?,
                jsonObject["nif"] as? String?,
                jsonObject["genero"] as? String?,
                jsonObject["idade"] as? Int?,
                jsonObject["localidade"] as? String?,
                jsonObject["concelho"] as? String?,
                jsonObject["isAdmin"] as Boolean
            )
        }
    }
}