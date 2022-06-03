package com.grupo1.food4u_nav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.data.CartItem
import org.w3c.dom.Text
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quantidade = itemView.findViewById<TextView>(R.id.productOrderNumber)
    }

    private val cart = emptyList<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_order, parent, false))
        return view
    }

    override fun getItemCount(): Int {
        return cart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cart[position]
        holder.quantidade.text = cart[position].quantidade.toString()
    }
}