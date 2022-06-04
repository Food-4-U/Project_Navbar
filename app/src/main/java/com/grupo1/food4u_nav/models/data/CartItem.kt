package com.grupo1.food4u_nav.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var item_id: Int?,
    var quantidade: Int?,
    var precoTotal: Double?
)



