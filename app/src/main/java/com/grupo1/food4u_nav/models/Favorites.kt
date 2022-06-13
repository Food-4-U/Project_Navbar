package com.grupo1.food4u_nav.models

import org.json.JSONObject

class Favorites {
    constructor(id_favoritos: Int?, itens_id_item: Int?, favorito: Boolean?, clientes_id: Int?) {
        this.id_favoritos = id_favoritos
        this.itens_id_item = itens_id_item
        this.favorito = favorito
        this.clientes_id = clientes_id
    }

    var id_favoritos : Int? = null
    var itens_id_item : Int? = null
    var favorito : Boolean? = null
    var clientes_id : Int? = null


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_favoritos", id_favoritos)
        jsonObject.put("itens_id_item", itens_id_item)
        jsonObject.put("favorito", favorito)
        jsonObject.put("clientes_id", clientes_id)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): Favorites{
            return Favorites(
                jsonObject["id_favoritos"] as? Int?,
                jsonObject["itens_id_item"] as? Int?,
                jsonObject["favorito"] as? Boolean?,
                jsonObject["clientes_id"] as? Int?
            )
        }
    }
}
