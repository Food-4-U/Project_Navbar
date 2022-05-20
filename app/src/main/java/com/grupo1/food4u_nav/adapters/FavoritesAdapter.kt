package com.grupo1.food4u_nav.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu

class FavoritesAdapter(val fav: List<Item_Menu>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO ADD OUTROS ATRIBUTOS
        var foodname = itemView.findViewById<TextView>(R.id.favorites_name)
        var foodPrice = itemView.findViewById<TextView>(R.id.favorites_price)

        // var photoFood = itemView.findViewById<ShapeableImageView>(R.id.favorites_photo)
       // var nameFood = itemView.findViewById<TextView>(R.id.favorites_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_favorites, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        holder.foodname.text = fav[position].description
        holder.foodPrice.text = fav[position].price.toString().plus("€")
    }

    override fun getItemCount(): Int {
        return fav.size
    }

}