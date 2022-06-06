package com.grupo1.food4u_nav.adapters

import Backend
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Index
import com.grupo1.food4u_nav.OrderActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.models.data.CartItem
import com.grupo1.food4u_nav.models.data.CartViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import org.w3c.dom.Text
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class OrderAdapter(context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quantidade = itemView.findViewById<TextView>(R.id.productOrderNumber)
        var nome = itemView.findViewById<TextView>(R.id.productNameOrderRow)
        var price = itemView.findViewById<TextView>(R.id.productOrderPrice)
        var photoFood = itemView.findViewById<ImageView>(R.id.productImageOrderRow)
        var buttonPlus = itemView.findViewById<ImageView>(R.id.productOrderPlusIcon)
        var buttonMinus = itemView.findViewById<ImageView>(R.id.productOrderMinusIcon)
        var totalText = itemView.findViewById<TextView>(R.id.orderTotal)
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

            var imageURL = it.url
            Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)
            var price = (it.preco!! * (cart[position].quantidade!!))
            var priceText = String.format("%.2f", price)
            holder.price.text = priceText.plus(" €")


            holder.buttonPlus.setOnClickListener{
                cart[position].quantidade = cart[position].quantidade?.plus(1)
                setData(cart)

                getTotal(holder)
                var total = getTotal(holder)
                var priceText = String.format("%.2f", total)
                holder.totalText.text = priceText


            }
            holder.buttonMinus.setOnClickListener{
                if (cart[position].quantidade!! >= 2)
                {
                    cart[position].quantidade = cart[position].quantidade?.minus(1)
                    setData(cart)
                    var total = getTotal(holder)
                    var priceText = String.format("%.2f", total)
                    holder.totalText.text = priceText.plus(" €")
                }
            }

        }

    }

    fun setData(cart: List<CartItem>) {
        this.cart = cart
        notifyDataSetChanged()
    }

    fun getTotal(holder: ViewHolder): Double {
        var total = 0.0

        for (i in 1..cart.size)
        {
            total += cart[i - 1].precoTotal!!
        }

        return total
    }
}