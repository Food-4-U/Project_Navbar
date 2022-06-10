package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Ingredients
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories

class IngredientsAdapter(val itens: List<String>, val context: Context) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ingredientName = itemView.findViewById<TextView>(R.id.ingredientText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_ingredients, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {

        var isClickable = false

        holder.ingredientName.text = itens[position]

        var observ = context.getSharedPreferences("Observ",
            Context.MODE_PRIVATE
        )

        holder.itemView.setOnClickListener {

            if (isClickable == false) {
                isClickable = true
            } else {
                isClickable = false
            }

            if (isClickable) {
                holder.itemView.setBackgroundResource(R.drawable.ingredient_click)

                var observText = observ.getString("observ", "")

                if (observText!!.length < 3){
                    var string = "Sem ${itens[position]}"
                    observText = string
                } else if (!(observText!!.contains(itens[position])))
                {
                    observText.plus(", Sem ${itens[position]}")
                }

                val myEdit = observ.edit()
                myEdit.clear()
                myEdit.putString("observ", observText)
                myEdit.apply()
            }
            else {
                holder.itemView.setBackgroundResource(R.drawable.profile_formatdetail)
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}