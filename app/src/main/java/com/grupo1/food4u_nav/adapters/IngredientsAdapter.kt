package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories

class IngredientsAdapter (val item: Item_Menu) : RecyclerView.Adapter<SubCategoriesAdapterHome.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<TextView>(R.id.ingredientText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_ingredients, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoriesAdapterMenu.ViewHolder, position: Int) {
        holder.categoryName.text =

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}