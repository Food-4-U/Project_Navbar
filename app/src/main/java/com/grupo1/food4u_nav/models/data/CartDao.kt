package com.grupo1.food4u_nav.models.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartItem: CartItem)

    @Query("SELECT * FROM cart ORDER BY id ASC")
    fun readCart(): LiveData<List<CartItem>>
}