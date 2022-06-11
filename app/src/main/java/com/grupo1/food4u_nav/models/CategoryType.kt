package com.grupo1.food4u_nav.models

import org.json.JSONObject

class CategoryType {

    constructor(id: Int?, name: String) {
        this.id = id
        this.name = name
    }

    var id: Int?
    var name: String


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_categoria", id)
        jsonObject.put("nome", name)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): CategoryType {
            return CategoryType (
                jsonObject["id_categoria"] as Int,
                jsonObject["nome"] as String
            )
        }
    }
}