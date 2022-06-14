package com.grupo1.food4u_nav.models

import androidx.fragment.app.FragmentActivity
import org.json.JSONObject
import java.util.*

class CardNumber {

    constructor(
        id_cartao: Int?,
        number: String?,
        numberName: String?,
        date: String?,
        cvc: Int?,
        id_cliente: Int?
    ) {
        this.id_cartao = id_cartao
        this.number = number
        this.numberName = numberName
        this.date = date
        this.cvc = cvc
        this.id_cliente = id_cliente
    }

    var id_cartao: Int? = null
    var number: String? = null
    var numberName: String? = null
    var date: String? = null
    var cvc: Int? = null
    var id_cliente: Int? = null

    fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_cartao", id_cartao)
        jsonObject.put("numero", number)
        jsonObject.put("nome_cartao", numberName)
        jsonObject.put("data_vencimento", date)
        jsonObject.put("cvc", cvc)
        jsonObject.put("id_cliente", id_cliente)
        return jsonObject
    }

    companion object {
        fun fromJSON(jsonObject: JSONObject): CardNumber {
            return CardNumber(
                jsonObject["id_cartao"] as? Int?,
                jsonObject["numero"] as? String?,
                jsonObject["nome_cartao"] as? String?,
                jsonObject["data_vencimento"] as? String?,
                jsonObject["cvc"] as? Int?,
                jsonObject["id_cliente"] as? Int?
            )
        }
    }
}