package com.grupo1.food4u_nav.models.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val repository: CartRepository
    private val readCart: LiveData<List<CartItem>>

    init {
        val cartDao = CartDatabase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
        readCart = repository.readCart
    }

    fun addToCart(cartItem: CartItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.addToCart(cartItem)
        }
    }
}