package com.grupo1.food4u_nav.models

import org.json.JSONObject

class Cliente {
    var id_cliente : Int? = null
    var nome : String? = null
    var email : String? = null
    var password : String? = null

    constructor(
        id_cliente : Int,
        nome : String?,
        email : String?,
        password : String?
    ) {
        this.id_cliente = id_cliente
        this.nome = nome
        this.email = email
        this.password = password
    }

    fun toJSON() : JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("id_cliente", id_cliente)
        jsonObject.put("nome", nome)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        return jsonObject
    }

    //
    companion object{
        fun fromJSON(jsonObject: JSONObject): Cliente{
            return Cliente(
                jsonObject["id_cliente"] as Int,
                jsonObject["nome"] as? String?,
                jsonObject["email"] as? String?,
                jsonObject["password"] as? String?
            )
        }
    }
}