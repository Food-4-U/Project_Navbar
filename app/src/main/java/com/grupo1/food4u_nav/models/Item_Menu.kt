package com.grupo1.food4u_nav.models

import org.json.JSONObject
import java.sql.Blob

class Item_Menu {

    var id_item: Int? = null
    var nome: String? = null
    var preco: Double? = null
    var temp_prep: Int? = null
    var destaque : Boolean = false
    var url : String? = null
    var id_categoria: Int? = null
    var id_subcategoria: Int? = null
    var avaliacao: Double? = null
    //var fav : Boolean

    constructor(
        id_item: Int?,
        nome: String?,
        preco: Double?,
        temp_prep: Int?,
        destaque: Boolean,
        url: String?,
        id_categoria: Int?,
        id_subcategoria: Int?,
        avaliacao: Double?
        //fav: Boolean
    ) {
        this.id_item = id_item
        this.nome = nome
        this.preco = preco
        this.temp_prep = temp_prep
        this.destaque = destaque
        this.url = url
        this.id_categoria = id_categoria
        this.id_subcategoria = id_subcategoria
        this.avaliacao = avaliacao
        //this.fav = fav
    }

    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_item", id_item)
        jsonObject.put("nome", nome)
        jsonObject.put("preco", preco)
        jsonObject.put("temp_prep", temp_prep)
        jsonObject.put("destaque", destaque)
        jsonObject.put("url", url)
        jsonObject.put("id_categoria", id_categoria)
        jsonObject.put("id_subcategoria", id_subcategoria)
        jsonObject.put("avaliacao", avaliacao)
        return jsonObject
    }

    //
    companion object{
        fun fromJSON (jsonObject: JSONObject): Item_Menu {
            return Item_Menu (
                jsonObject["id_item"] as? Int?,
                jsonObject["nome"] as? String?,
                jsonObject["preco"] as? Double?,
                jsonObject["temp_prep"] as? Int?,
                jsonObject["destaque"] as Boolean,
                jsonObject["url"] as? String?,
                jsonObject["id_categoria"] as? Int?,
                jsonObject["id_subcategoria"] as? Int?,
                jsonObject["avaliacao"] as? Double?
            )
        }
    }
}