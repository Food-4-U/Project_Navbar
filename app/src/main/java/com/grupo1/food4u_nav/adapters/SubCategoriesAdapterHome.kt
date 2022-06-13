package com.grupo1.food4u_nav.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.SubCategories
import com.squareup.picasso.Picasso


class SubCategoriesAdapterHome (val itens: List<SubCategories>) : RecyclerView.Adapter<SubCategoriesAdapterHome.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subcategoryName = itemView.findViewById<TextView>(com.grupo1.food4u_nav.R.id.subCategory_name)
        var subcategoryPhoto = itemView.findViewById<ImageView>(R.id.imageView12)
        var subcategory_photo = itemView.findViewById<ImageView>(R.id.subcategory_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.grupo1.food4u_nav.R.layout.row_subcategories, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoriesAdapterHome.ViewHolder, position: Int) {
        holder.subcategoryName.text = itens[position].name

        var url = itens[position].url

        Picasso.get().load(url).fit().centerCrop().into(holder.subcategory_photo)
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}