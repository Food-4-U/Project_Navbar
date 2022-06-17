package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.ItensPedido
import com.grupo1.food4u_nav.models.PedidoItensFatura

class OrderDetailAdapter(val context: Context, var pedido : List<PedidoItensFatura>) : RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName = itemView.findViewById<TextView>(R.id.produtoNameFaturaROW)
        var productQtd = itemView.findViewById<TextView>(R.id.quantidadeFaturaROW)
        var productPrice = itemView.findViewById<TextView>(R.id.precoROW)
        var productTotal = itemView.findViewById<TextView>(R.id.totalPrice)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_fatura, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.productName.text = pedido[position].nome
            holder.productQtd.text = pedido[position].qtd.toString()
            holder.productPrice.text = pedido[position].preco.toString().plus("€")
            holder.productTotal.text = (pedido[position].qtd!! * pedido[position].preco!!).toString().plus("€")


    }

    override fun getItemCount(): Int {
        return pedido.size;
    }
}