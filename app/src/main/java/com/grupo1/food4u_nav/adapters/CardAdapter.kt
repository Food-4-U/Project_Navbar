package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.CardNumber

class CardAdapter(val context: Context, private val cards : List<CardNumber>): RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var buttonAddCard = itemView.findViewById<Button>(R.id.imageView37)
        var cardNumber = itemView.findViewById<TextView>(R.id.textView63)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardNumber.text = cards[position].number
        holder.buttonAddCard.setOnClickListener{
            Toast.makeText(context, "oi", Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int {
        return cards.size
    }


}