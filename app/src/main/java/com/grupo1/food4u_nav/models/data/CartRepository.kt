package com.grupo1.food4u_nav.models.data

import androidx.lifecycle.LiveData
import kotlin.collections.List as List

class CartRepository(private val cartDao: CartDao) {

    val readCart: LiveData<List<CartItem>> = cartDao.readCart()

    suspend fun addToCart(cartItem: CartItem){
        cartDao.addToCart(cartItem)
    }

    suspend fun updateItem(cartItem: CartItem){
        cartDao.updateItem(cartItem)
    }

    suspend fun deleteItem(cartItem: CartItem){
        cartDao.delete(cartItem)
    }

    suspend fun deleteCart(cart :List<CartItem>){
        cartDao.deleteCart(cart)
    }

}