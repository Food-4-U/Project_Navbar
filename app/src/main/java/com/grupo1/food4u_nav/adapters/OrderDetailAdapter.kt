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
        var productName = itemView.findViewById<TextView>(R.id.produtoNameFaturaROW)
        var productQtd = itemView.findViewById<TextView>(R.id.quantidadeFaturaROW)
        // preço unit
        var productTotal = itemView.findViewById<TextView>(R.id.precoROW)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_fatura, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.productName =
        //holder.productQtd =
        //preço unit
        //holder productTotal =


    }
}