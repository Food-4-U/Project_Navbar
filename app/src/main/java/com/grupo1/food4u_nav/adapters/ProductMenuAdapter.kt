package com.grupo1.food4u_nav.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.internal.ContextUtils.getActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Favorites
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import com.squareup.picasso.Picasso


class ProductMenuAdapter(val context: Context, val itens: List<Item_Menu>) : RecyclerView.Adapter<ProductMenuAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ImageView>(R.id.productPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.productTitle)
        var foodprice = itemView.findViewById<TextView>(R.id.priceProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ProductMenuAdapter.ViewHolder, position: Int) {
        var price = String.format("%.2f", itens[position].preco)
        holder.foodprice.text = price.plus("€")
        holder.nameFood.text = itens[position].nome

        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", itens[position].id_item)
            context.startActivity(intent)
        }

       /* holder.isChecked = itens[position].

        changeIconFav(position,holder)

        holder.likebtnMenu.setOnClickListener {
            changeIconFav(position,holder)
        }*/
    }


    override fun getItemCount(): Int {
        return itens.size
    }


    /*fun changeIconFav(position: Int,holder: ProductMenuAdapter.ViewHolder){
        for (i in 0 until fav.size){
            if (itens[position].id_item == fav[i].itens_id_item && )
        }
        if (itens[position].id_item == fav){
            holder.likeBtn.setImageResource(R.drawable.ic_heartfill)
            fav[position].fav = false
        }
        else{
            holder.likeBtn.setImageResource(R.drawable.ic_heartnotfill)
            fav[position].fav = true
        }
    }*/
}