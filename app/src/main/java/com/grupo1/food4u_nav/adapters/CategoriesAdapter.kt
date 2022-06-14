package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.grupo1.food4u_nav.ItensCategoryActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.CategoryType
import com.squareup.picasso.Picasso
import java.security.AccessController.getContext

class CategoriesAdapter (val context : Context, val itens: List<CategoryType>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<TextView>(R.id.categoryName)
        var category_photo = itemView.findViewById<ImageView>(R.id.categoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_categories, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = itens[position].name
        var url = itens[position].url

        Picasso.get().load(url).fit().centerCrop().into(holder.category_photo)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, itens[position].id)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, itens[position].name)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}