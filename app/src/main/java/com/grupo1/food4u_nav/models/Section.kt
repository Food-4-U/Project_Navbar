package com.grupo1.food4u_nav.models

import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu

data class Section (
 var categoria : String,
 var itens : List<Item_Menu>
)