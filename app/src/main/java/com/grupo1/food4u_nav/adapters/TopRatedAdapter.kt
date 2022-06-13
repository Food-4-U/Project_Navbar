package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.models.data.CartItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopRatedAdapter (val context: Context, val itens: List<Item_Menu>) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.menu_foodPhoto2)
        var nameFood = itemView.findViewById<TextView>(R.id.menu_nameFood2)
        var foodprice = itemView.findViewById<TextView>(R.id.menu_foodPrice2)
        var foodEvaluation = itemView.findViewById<TextView>(R.id.menu_foodEvauation2)
        var foodStars = itemView.findViewById<RatingBar>(R.id.ratingBar_hottest2)
        var buttonAdd = itemView.findViewById<ImageView>(R.id.buttonAddTop)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_toprated, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        var price = String.format("%.2f", itens[position].preco)
        holder.nameFood.text = itens[position].nome
        holder.foodEvaluation.text = itens[position].avaliação.toString()
        holder.foodprice.text = price.plus("€")
        holder.foodStars.rating = itens[position].avaliação!!.toFloat()

        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(900,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", itens[position].id_item)
            context.startActivity(intent)
        }

        holder.buttonAdd.setOnClickListener {
            var cartItem = CartItem(null, itens[position].id_item, 1, itens[position].preco)
            GlobalScope.launch(Dispatchers.IO) {
                CartDatabase.getDatabase(context).cartDao().addToCart(cartItem)
            }
            Toast.makeText(
                context,
                "Adicionado ao Pedido.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}