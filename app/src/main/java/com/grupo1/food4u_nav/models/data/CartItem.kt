package com.grupo1.food4u_nav.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grupo1.food4u_nav.models.Item_Menu

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    var item_id: Int? ,
    var quantidade: Int?
)



