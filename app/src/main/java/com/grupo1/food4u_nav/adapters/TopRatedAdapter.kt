package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.R
import projeto.ipca.food4u.grupoI.models.Item_Menu

class TopRatedAdapter (val itens: List<Item_Menu>) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.menu_foodPhoto2)
        var nameFood = itemView.findViewById<TextView>(R.id.menu_nameFood2)
        var foodprice = itemView.findViewById<TextView>(R.id.menu_foodPrice2)
        var foodEvaluation = itemView.findViewById<TextView>(R.id.menu_foodEvauation2)
        var foodStars = itemView.findViewById<RatingBar>(R.id.ratingBar_hottest2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_toprated, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        holder.nameFood.text = itens[position].description
        holder.foodEvaluation.text = itens[position].evaluation.toString()
        holder.foodprice.text = itens[position].price.toString() + "€"
        holder.foodStars.rating = itens[position].evaluation
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}