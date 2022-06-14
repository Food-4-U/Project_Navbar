package com.grupo1.food4u_nav.models

import androidx.fragment.app.FragmentActivity
import org.json.JSONObject

class CardNumber {

    constructor(id_cartao: FragmentActivity, number: List<CardNumber>, numberName: String?, date: String?, cvc: Int?, id_cliente: Int?) {
        this.id_cartao = id_cartao
        this.number = number
        this.numberName = numberName
        this.date = date
        this.cvc = cvc
        this.id_cliente = id_cliente
    }

    var id_cartao : Int? = null
    var number : String? = null
    var numberName : String? = null
    var date : String? = null
    var cvc : Int? = null
    var id_cliente : Int? = null

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_cartao", id_cartao)
        jsonObject.put("number", number)
        jsonObject.put("numberName", numberName)
        jsonObject.put("date", date)
        jsonObject.put("cvc", cvc)
        jsonObject.put("id_cliente", id_cliente)
        return jsonObject
    }

    companion object{
        fun fromJSON(jsonObject: JSONObject): CardNumber {
            return CardNumber (
                jsonObject["id_cartao"] as? Int?,
                jsonObject["number"] as? String?,
                jsonObject["numberName"] as? String?,
                jsonObject["date"] as? String?,
                jsonObject["cvc"] as? Int?,
                jsonObject["id_cliente"] as? Int?
            )
        }
    }
}