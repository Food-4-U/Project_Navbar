package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.ItensPedido

class OrderDetailAdapter(val pedido: ItensPedido) : RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardNumber = itemView.findViewById<TextView>(R.id.textView63)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_orderitens, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pedido.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardNumber.text = cards[position].number


    }
}