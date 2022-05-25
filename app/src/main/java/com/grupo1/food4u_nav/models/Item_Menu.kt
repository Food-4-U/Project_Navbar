package com.grupo1.food4u_nav.models

import org.json.JSONObject
import java.sql.Blob

class Item_Menu {
    constructor(
        id_item: Int,
        nome: String,
        preco: Float,
        temp_prep: Int,
        avaliacao: Float,
        url: String,
        destaque: Boolean,
        id_categoria: Int,
        id_subcategoria: Int,
        fav: Boolean
    ) {
        this.id_item = id_item
        this.nome = nome
        this.preco = preco
        this.temp_prep = temp_prep
        this.avaliacao = avaliacao
        this.url = url
        this.destaque = destaque
        this.id_categoria = id_categoria
        this.id_subcategoria = id_subcategoria
        this.fav = fav
    }


    var id_item:Int
    var nome: String
    var preco: Float
    var temp_prep: Int
    var avaliacao: Float
    var url : String
    var destaque : Boolean
    var id_categoria: Int
    var id_subcategoria: Int
    var fav : Boolean


    fun toJSON() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id_item", id_item)
        jsonObject.put("nome", nome)
        jsonObject.put("preco", preco)
        jsonObject.put("temp_prep", temp_prep)
        jsonObject.put("avaliacao", avaliacao)
        jsonObject.put("url", url)
        jsonObject.put("destaque", destaque)
        jsonObject.put("id_categoria", id_categoria)
        jsonObject.put("id_subcategoria", id_subcategoria)
        jsonObject.put("fav", fav)
        return jsonObject
    }

    //
    companion object{
        fun fromJSON(jsonObject: JSONObject): Item_Menu{
            return Item_Menu(
                jsonObject["id_item"] as Int,
                jsonObject["nome"] as String,
                jsonObject["preco"] as Float,
                jsonObject["temp_prep"] as Int,
                jsonObject["avaliacao"] as Float,
                jsonObject["url"] as String,
                jsonObject["destaque"] as Boolean,
                jsonObject["id_categoria"] as Int,
                jsonObject["id_subcategoria"] as Int,
                jsonObject["fav"] as Boolean
            )
        }
    }

}

