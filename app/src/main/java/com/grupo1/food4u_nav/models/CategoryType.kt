package com.grupo1.food4u_nav.models

import org.json.JSONObject

class CategoryType {

    constructor(id: Int?, name: String, url: String?) {
        this.id = id
        this.name = name
        this.url = url
    }

    var id: Int?
    var name: String
    var url: String?


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_categoria", id)
        jsonObject.put("nome", name)
        jsonObject.put("url", url)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): CategoryType {
            return CategoryType (
                jsonObject["id_categoria"] as Int,
                jsonObject["nome"] as String,
                jsonObject["url"] as? String?
            )
        }
    }
}