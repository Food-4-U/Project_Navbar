package com.grupo1.food4u_nav.models.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartItem: CartItem)

    @Update
    suspend fun updateItem(cartItem: CartItem)

    @Query("SELECT * FROM cart ORDER BY id ASC")
    fun readCart(): LiveData<List<CartItem>>

    @Delete
    fun delete(cartItem: CartItem)
}