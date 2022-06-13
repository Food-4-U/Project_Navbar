package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.SubCategories

class SubCategoriesAdapterMenu (val itens: List<SubCategories>) : RecyclerView.Adapter<SubCategoriesAdapterMenu.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<TextView>(R.id.categoryText)
        var scrollView = itemView.findViewById<ScrollView>(R.id.scrollView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoriesAdapterMenu.ViewHolder, position: Int) {
        holder.categoryName.text = itens[position].name
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}
