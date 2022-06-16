package com.grupo1.food4u_nav.models

import org.json.JSONObject

class PedidoItensFatura {
    constructor( nome: String?, qtd: Int?, preco: Double?) {
        this.nome = nome
        this.qtd = qtd
        this.preco = preco
    }

    var nome: String?
    var qtd: Int?
    var preco: Double?


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("nome", nome)
        jsonObject.put("qtd", qtd)
        jsonObject.put("preco", qtd)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): PedidoItensFatura {
            return PedidoItensFatura (
                jsonObject["nome"] as String,
                jsonObject["qtd"] as Int,
                jsonObject["preco"] as? Double
            )
        }
    }
}