package com.grupo1.food4u_nav.adapters

import Backend
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.OrderActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.models.data.CartItem
import com.grupo1.food4u_nav.models.data.CartViewModel
import com.squareup.picasso.Picasso
import androidx.lifecycle.lifecycleScope
import androidx.room.Index
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderAdapter(val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quantidade = itemView.findViewById<TextView>(R.id.productOrderNumber)
        var nome = itemView.findViewById<TextView>(R.id.productNameOrderRow)
        var price = itemView.findViewById<TextView>(R.id.productOrderPrice)
        var photoFood = itemView.findViewById<ImageView>(R.id.productImageOrderRow)
        var buttonPlus = itemView.findViewById<ImageView>(R.id.productOrderPlusIcon)
        var buttonMinus = itemView.findViewById<ImageView>(R.id.productOrderMinusIcon)
        var total = itemView.findViewById<TextView>(R.id.orderTotal1)
        var anota = itemView.findViewById<TextView>(R.id.anotacoesOrderRow)
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

        var prefs = context.getSharedPreferences("Observ",
            Context.MODE_PRIVATE
        )

        holder.anota.text = prefs.getString(cart[position].item_id.toString(), "")


        Backend.getItemID(cart[position].item_id!!){
            holder.nome.text = it.nome

            var imageURL = it.url
            Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)
            var price = (it.preco!! * (cart[position].quantidade!!))
            var priceUnit = it.preco
            var priceText = String.format("%.2f", price)
            holder.price.text = priceText.plus(" €")


            holder.buttonPlus.setOnClickListener{
                cart[position].quantidade = cart[position].quantidade?.plus(1)
                cart[position].precoTotal = cart[position].quantidade!! * priceUnit!!
                setData(cart)
                var cartItem = cart[position]


                GlobalScope.launch(Dispatchers.IO) {
                    CartDatabase.getDatabase(context)?.cartDao().updateItem(cartItem)
                }
            }

            holder.buttonMinus.setOnClickListener{
                if (cart[position].quantidade!! >= 2)
                {
                    cart[position].quantidade = cart[position].quantidade?.minus(1)
                    cart[position].precoTotal = cart[position].quantidade!! * priceUnit!!
                    setData(cart)
                    var cartItem = cart[position]

                    GlobalScope.launch(Dispatchers.IO) {
                        CartDatabase.getDatabase(context)?.cartDao().updateItem(cartItem)

                    }
                }
                else {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Confirmação")
                    builder.setMessage("Tem a certeza que pretende eliminar este item?")

                    builder.setPositiveButton(R.string.yes) { dialog, which ->
                        GlobalScope.launch(Dispatchers.IO) {
                            CartDatabase.getDatabase(context).cartDao().delete(cartItem)
                            var observ = context.getSharedPreferences("Observ", Context.MODE_PRIVATE).edit().clear().apply()
                        }
                    }

                    builder.setNegativeButton(R.string.no) { dialog, which ->
                    }

                    builder.show()
                }
            }

        }

    }

    fun setData(cart: List<CartItem>) {
        this.cart = cart
        notifyDataSetChanged()
    }

    fun totalCart(context: Context, cartList: List<CartItem>): Double {
        var total = 0.0

        for (i in 1..cartList.size){
            total += cartList[i - 1].precoTotal!!
        }


        return total
    }

}