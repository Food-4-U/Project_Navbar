package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.SubCategories

class SubCategoriesAdapterMenu (val itens: List<SubCategories>) : RecyclerView.Adapter<SubCategoriesAdapterMenu.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subcategoryName = itemView.findViewById<TextView>(R.id.subCategory_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_subcategories, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoriesAdapterMenu.ViewHolder, position: Int) {
        holder.subcategoryName.text = itens[position].name
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}
