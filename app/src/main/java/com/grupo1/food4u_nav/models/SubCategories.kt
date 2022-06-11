package com.grupo1.food4u_nav.models

import org.json.JSONObject

class SubCategories {
    constructor(id_SubCategory: Int?, name: String?, url: String?) {
        this.id_SubCategory = id_SubCategory
        this.name = name
        this.url = url
    }

    var id_SubCategory : Int? = null
    var name : String? = null
    var url : String? = null

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_subcategoria", id_SubCategory)
        jsonObject.put("nome", name)
        jsonObject.put("url", url)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): SubCategories {
            return SubCategories(
                jsonObject["id_subcategoria"] as? Int?,
                jsonObject["nome"] as? String?,
                jsonObject["url"] as? String?

            )
        }
    }
}