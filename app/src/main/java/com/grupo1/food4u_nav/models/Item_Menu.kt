package com.grupo1.food4u_nav.models

class Item_Menu {

    constructor(description: String, time_prepare: Int, evaluation: Float, price: Float, fav:Boolean) {
        this.description = description
        this.time_prepare = time_prepare
        this.evaluation = evaluation
        this.price = price
        this.fav = fav
    }

    var description: String
    var time_prepare: Int
    var evaluation: Float
    var price : Float
    var fav : Boolean

}