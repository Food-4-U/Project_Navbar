package com.grupo1.food4u_nav.adapters

import Backend
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.data.CartItem
import okhttp3.internal.notify
import org.w3c.dom.Text
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class OrderAdapter(context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quantidade = itemView.findViewById<TextView>(R.id.productOrderNumber)
        var nome = itemView.findViewById<TextView>(R.id.productNameOrderRow)
        var price = itemView.findViewById<TextView>(R.id.productOrderPrice)
    }

    private var cart = emptyList<CartItem>()

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

        Backend.getItemID(cart[position].item_id!!){
            holder.nome.text = it.nome

            var price = (it.preco!! * (cart[position].quantidade!!))
            var priceText = String.format("%.2f", price)
            holder.price.text = priceText.plus(" €")
        }

    }

    fun setData(cart: List<CartItem>) {
        this.cart = cart
        notifyDataSetChanged()
    }

    fun getTotal(): Double {
        var total= 0.0

        for (i in 1..cart.size){
            Backend.getItemID(cart[i].item_id!!){
                var price = (it.preco!! * (cart[i].quantidade!!))

                total += price
            }
        }

        return total
    }
}