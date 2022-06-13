package com.grupo1.food4u_nav.models

import org.json.JSONObject

class ItensPedido {

    constructor(id_pedido_item: Int?, id_pedido: Int?, id_item: Int?, qtd: Int?) {
        this.id_pedido_item = id_pedido_item
        this.id_pedido = id_pedido
        this.id_item = id_item
        this.qtd = qtd
    }

    var id_pedido_item: Int?
    var id_pedido: Int?
    var id_item: Int?
    var qtd: Int?


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_pedido_item", id_pedido_item)
        jsonObject.put("id_pedido", id_pedido)
        jsonObject.put("id_item", id_item)
        jsonObject.put("qtd", qtd)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): ItensPedido {
            return ItensPedido (
                jsonObject["id_pedido_item"] as Int,
                jsonObject["id_pedido"] as Int,
                jsonObject["id_item"] as Int,
                jsonObject["qtd"] as? Int
            )
        }
    }
}