package com.grupo1.food4u_nav.models

import org.json.JSONObject

class SubCategories {
    constructor(id_SubCategory: Int?, name: String?) {
        this.id_SubCategory = id_SubCategory
        this.name = name
    }

    var id_SubCategory : Int? = null
    var name : String? = null
    //var id_Category : Int?

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_subcategoria", id_SubCategory)
        jsonObject.put("nome", name)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): SubCategories {
            return SubCategories(
                jsonObject["id_subcategoria"] as? Int?,
                jsonObject["nome"] as? String?
            )
        }
    }
}