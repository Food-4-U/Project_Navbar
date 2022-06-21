

package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Favorites
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.models.data.CartItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class FavoritesAdapter(val context: Context, val itens: List<Item_Menu>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.favorites_photo)
        var foodname = itemView.findViewById<TextView>(R.id.favorites_name)
        var foodPrice = itemView.findViewById<TextView>(R.id.favorites_price)
        var ratingBar_fav = itemView.findViewById<RatingBar>(R.id.ratingBar_fav)
        var rating = itemView.findViewById<TextView>(R.id.rating)
        var addCart = itemView.findViewById<ImageView>(R.id.addCart)

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

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", itens[position].id_item)
            context.startActivity(intent)
        }

        holder.addCart.setOnClickListener {
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