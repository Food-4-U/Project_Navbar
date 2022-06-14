package com.grupo1.food4u_nav.adapters

import Backend
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.CardNumber

class cardAdapter(val context: Context): RecyclerView.Adapter<cardAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var buttonAddCard = itemView.findViewById<ImageView>(R.id.imageView38)
        var cardNumber = itemView.findViewById<TextView>(R.id.textView63)

    }

    private var card = emptyList<CardNumber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = cardAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_rv, parent, false))
        return view
    }

    override fun getItemCount(): Int {
        return card.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Backend.GetCard(context.getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0) ) {
            var cartoes = it

            holder.cardNumber.text = cartoes[position].number
        }








    }
}