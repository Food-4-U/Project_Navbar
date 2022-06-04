package com.grupo1.food4u_nav.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.internal.ContextUtils.getActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import com.grupo1.food4u_nav.ui.admin.EditItemFragment
import com.squareup.picasso.Picasso


class EditMenuAdapter(val context: Context, val itens: List<Item_Menu>) : RecyclerView.Adapter<EditMenuAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ImageView>(R.id.productPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.productTitle)
        var foodprice = itemView.findViewById<TextView>(R.id.priceProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_editmenu, parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: EditMenuAdapter.ViewHolder, position: Int) {
        var price = String.format("%.2f", itens[position].preco)
        holder.foodprice.text = price.plus("€")
        holder.nameFood.text = itens[position].nome

        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {

            val mFragmentManager = (context as FragmentActivity).supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = EditItemFragment()

            var bundle = Bundle()
            bundle.putInt("id_item", itens[position].id_item!!)
        }
    }


    override fun getItemCount(): Int {
        return itens.size
    }
}