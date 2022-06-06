package com.grupo1.food4u_nav.models

class CartItem {

    constructor(item: Item_Menu, quantidade: Int) {
        this.item = item
        this.quantidade = quantidade
    }

    var item: Item_Menu
    var quantidade: Int


    fun getQuantity(): Int {
        return quantidade
    }

    fun setQuantity(quantity: Int) {
        this.quantidade = quantidade
    }

    fun getProduct(): Item_Menu? {
        return item
    }

    fun setProduct(item: Item_Menu) {
        this.item = item
    }

}