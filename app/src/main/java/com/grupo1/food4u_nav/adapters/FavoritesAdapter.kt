

package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Favorites
import com.grupo1.food4u_nav.models.Item_Menu
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class FavoritesAdapter(val itens: List<Item_Menu>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.favorites_photo)
        var foodname = itemView.findViewById<TextView>(R.id.favorites_name)
        var foodPrice = itemView.findViewById<TextView>(R.id.favorites_price)
        var ratingBar_fav = itemView.findViewById<RatingBar>(R.id.ratingBar_fav)
        var rating = itemView.findViewById<TextView>(R.id.rating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_favorites, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        holder.foodname.text = itens[position].nome
        holder.foodPrice.text = String.format("%.2f", itens[position].preco).plus("€")
        var imageURL = itens[position].url

        Picasso.get().load(imageURL).resize(100,90).into(holder.photoFood)

        holder.ratingBar_fav.rating = itens[position].avaliação!!.toFloat()
        holder.rating.text = itens[position].avaliação!!.toString()

    }

    override fun getItemCount(): Int {
        return itens.size
    }

}