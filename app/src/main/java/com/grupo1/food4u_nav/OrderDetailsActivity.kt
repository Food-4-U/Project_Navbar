package com.grupo1.food4u_nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.adapters.OrderAdapter
import com.grupo1.food4u_nav.adapters.OrderDetailAdapter
import com.grupo1.food4u_nav.models.PedidoItensFatura

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val procedeBtn = findViewById<Button>(R.id.button3)

        var id_pedido = intent.getIntExtra("id_pedido", 0)

        val rv_Order : RecyclerView = findViewById(R.id.recicleFatura)
        val adapter = OrderDetailAdapter(this, id_pedido)

        rv_Order.adapter = adapter
        rv_Order.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }
}