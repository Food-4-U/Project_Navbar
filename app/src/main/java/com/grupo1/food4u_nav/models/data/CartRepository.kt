package com.grupo1.food4u_nav.models.data

import androidx.lifecycle.LiveData

class CartRepository(private val cartDao: CartDao) {

    val readCart: LiveData<List<CartItem>> = cartDao.readCart()

    suspend fun addToCart(cartItem: CartItem){
        cartDao.addToCart(cartItem)
    }
}