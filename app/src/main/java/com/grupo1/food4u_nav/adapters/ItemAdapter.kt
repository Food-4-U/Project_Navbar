package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.squareup.picasso.Picasso

class ItemAdapter(private val context: Context, private val items: List<Item_Menu>) : RecyclerView.Adapter<ItemAdapter.CategoryItemViewHolder>() {

    class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ImageView>(R.id.productPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.productTitle)
        var foodprice = itemView.findViewById<TextView>(R.id.priceProduct)
        var likebtnMenu = itemView.findViewById<ImageView>(R.id.likebtnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_product, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.nameFood.text = items[position].nome
        holder.foodprice.text = String.format("%.2f", items[position].preco).plus("€")

        var imageURL = items[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", items[position].id_item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}