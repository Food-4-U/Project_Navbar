package com.grupo1.food4u_nav.models

import org.json.JSONObject
import java.text.DateFormat

class Pedido {

    var id_pedido: Int? = null
    var dataHora: Long? = null
    var total: Double? = null
    var pago: Boolean? = false
    var avaliação: Double? = null
    var aval_funcio : Double? = null
    var id_mesa : Int? = null
    var id_cliente: Int? = null

    constructor(
        id_pedido: Int?,
        dataHora: Long?,
        total: Double?,
        pago: Boolean?,
        avaliação: Double?,
        aval_funcio: Double?,
        id_mesa: Int?,
        id_cliente: Int?,

        //fav: Boolean
    ) {
        this.id_pedido = id_pedido
        this.dataHora = dataHora
        this.total = total
        this.pago = pago
        this.avaliação = avaliação
        this.aval_funcio = aval_funcio
        this.id_mesa = id_mesa
        this.id_cliente = id_cliente
    }

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_pedido", id_pedido)
        jsonObject.put("dataHora", dataHora)
        jsonObject.put("total", total)
        jsonObject.put("pago", pago)
        jsonObject.put("avaliação", avaliação)
        jsonObject.put("aval_funcio", aval_funcio)
        jsonObject.put("id_mesa", id_mesa)
        jsonObject.put("id_cliente", id_cliente)
        return jsonObject
    }

    //
    companion object{
        fun fromJSON (jsonObject: JSONObject): Pedido {
            return Pedido (
                jsonObject["id_item"] as? Int?,
                jsonObject["dataHora"] as? Long?,
                jsonObject["total"] as? Double?,
                jsonObject["pago"] as? Boolean,
                jsonObject["avaliação"] as? Double,
                jsonObject["aval_funcio"] as? Double?,
                jsonObject["id_mesa"] as? Int?,
                jsonObject["id_cliente"] as? Int?
            )
        }
    }
}