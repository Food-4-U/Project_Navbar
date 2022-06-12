package com.grupo1.food4u_nav.models

import org.json.JSONObject

class CardNumber {

    constructor(id_cartao: Int?, number: Float?, date: String?, cvc: Int?, id_cliente: Int?) {
        this.id_cartao = id_cartao
        this.number = number
        this.date = date
        this.cvc = cvc
        this.id_cliente = id_cliente
    }

    var id_cartao : Int? = null
    var number : Float? = null
    var date : String? = null
    var cvc : Int? = null
    var id_cliente : Int? = null

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_cartao", id_cartao)
        jsonObject.put("number", number)
        jsonObject.put("date", date)
        jsonObject.put("cvc", cvc)
        jsonObject.put("id_cliente", id_cliente)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): CardNumber {
            return CardNumber (
                jsonObject["id_cartao"] as? Int?,
                jsonObject["number"] as? Float?,
                jsonObject["date"] as? String?,
                jsonObject["cvc"] as? Int?,
                jsonObject["id_cliente"] as? Int?
            )
        }
    }
}