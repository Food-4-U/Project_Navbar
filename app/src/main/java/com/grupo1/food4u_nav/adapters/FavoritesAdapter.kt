package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu

class FavoritesAdapter(val fav: List<Item_Menu>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO ADD OUTROS ATRIBUTOS
        var foodname = itemView.findViewById<TextView>(R.id.favorites_name)
        var foodPrice = itemView.findViewById<TextView>(R.id.favorites_price)
        var isChecked : Boolean = false
        var likeBtn = itemView.findViewById<ImageButton>(R.id.likebtn)
        // var photoFood = itemView.findViewById<ShapeableImageView>(R.id.favorites_photo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_favorites, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        holder.foodname.text = fav[position].nome
        holder.foodPrice.text = fav[position].preco.toString().plus("€")
        holder.isChecked = fav[position].fav

        changeIconFav(position,holder)

        holder.likeBtn.setOnClickListener {
            changeIconFav(position,holder)
        }

    }

    override fun getItemCount(): Int {
        return fav.size
    }


    fun changeIconFav(position: Int,holder: FavoritesAdapter.ViewHolder){
        if (fav[position].fav){
            holder.likeBtn.setImageResource(R.drawable.ic_heartfill)
            fav[position].fav = false
        }
        else{
            holder.likeBtn.setImageResource(R.drawable.ic_heartnotfill)
            fav[position].fav = true
        }
    }

}