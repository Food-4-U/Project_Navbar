package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.OrderDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.ItensPedido
import com.grupo1.food4u_nav.models.Pedido
import com.grupo1.food4u_nav.models.SectionMenu
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RvOrderAdapter(val context: Context, val orders : List<Pedido>) : RecyclerView.Adapter<RvOrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderNum = itemView.findViewById<TextView>(R.id.orderNum)
        var qtdItems = itemView.findViewById<TextView>(R.id.qtdItems)
        var totalOrder = itemView.findViewById<TextView>(R.id.totalOrder)
        var state = itemView.findViewById<TextView>(R.id.state)
        var date = itemView.findViewById<TextView>(R.id.dateOrder)
        var details = itemView.findViewById<Button>(R.id.button4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_orderprofile, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvOrderAdapter.ViewHolder, position: Int) {
        holder.orderNum.text = orders[position].id_pedido.toString()
        holder.totalOrder.text = orders[position].total.toString().plus("€")

        holder.date.text= orders[position].dataHora
        holder.state.text = context.getString(R.string.payed)

        val id_pedido = orders[position].id_pedido.toString()
        Backend.GetItemsPedido(id_pedido) {
            var listItems = it
            var total = 0

            if (listItems.size > 0) {
                for (i in 1..listItems.size) {
                    total += listItems[i - 1].qtd!!
                }

                holder.qtdItems.text = total.toString()

                //inflate com a list
                holder.details.setOnClickListener {
                    val intent = Intent(context, OrderDetailsActivity::class.java)
                    intent.putExtra("id_pedido", id_pedido)
                    context.startActivity(intent)
                }
            }
        }
    }
    override fun getItemCount(): Int {
      return orders.size
    }
}