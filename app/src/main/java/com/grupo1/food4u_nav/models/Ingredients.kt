package com.grupo1.food4u_nav.models

import org.json.JSONObject

class Ingredients {

    constructor(id_ingrediente: Int?, name: String?, qtd: Int) {
        this.id_ingrediente = id_ingrediente
        this.name = name
        this.qtd = qtd
    }

    var id_ingrediente : Int? = null
    var name : String? = null
    var qtd = 1

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_ingrediente", id_ingrediente)
        jsonObject.put("nome", name)
        jsonObject.put("qtd", qtd)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): Ingredients {
            return Ingredients(
                jsonObject["id_ingrediente"] as? Int?,
                jsonObject["nome"] as? String?,
                jsonObject["qtd"] as Int
            )
        }
    }
}