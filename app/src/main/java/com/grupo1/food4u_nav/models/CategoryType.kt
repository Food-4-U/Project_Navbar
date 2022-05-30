package com.grupo1.food4u_nav.models

import org.json.JSONObject

class CategoryType {

    constructor(name: String, id: Int) {
        this.id = id
        this.name = name
    }

    var id: Int
    var name: String


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_category", id)
        jsonObject.put("nome", name)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): SubCategories {
            return SubCategories(
                jsonObject["id_category"] as? Int?,
                jsonObject["name"] as? String?
            )
        }
    }
}