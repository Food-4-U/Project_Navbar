package com.grupo1.food4u_nav.models

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


}