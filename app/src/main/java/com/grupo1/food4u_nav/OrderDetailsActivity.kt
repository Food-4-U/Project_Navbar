package com.grupo1.food4u_nav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.adapters.OrderAdapter
import com.grupo1.food4u_nav.adapters.OrderDetailAdapter
import com.grupo1.food4u_nav.models.PedidoItensFatura
import org.w3c.dom.Text

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val procedeBtn = findViewById<Button>(R.id.button3)
        val nomeClient = findViewById<TextView>(R.id.nomeClienteFatura)
        val nif = findViewById<TextView>(R.id.nifNumberFatura)
        val email = findViewById<TextView>(R.id.emailFatura)
        val data = findViewById<TextView>(R.id.dataFatura)
        val faturaNum = findViewById<TextView>(R.id.faturanumero)
        val total = findViewById<TextView>(R.id.totalMoneyFatura)
        val mesa = findViewById<TextView>(R.id.tableFatura)

        var id_pedido = intent.getIntExtra("id_pedido", 0)
        var id_cliente = getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)

        Backend.getClientes(id_cliente){
            var cliente = it

            nomeClient.text = cliente.nome
            nif.text = cliente.nif
            email.text = cliente.email
        }

        Backend.GetAllPedidos(id_cliente)
        {
            var pedidos = it[0]

            data.text = pedidos.dataHora
            faturaNum.text = "RF-" + pedidos.id_pedido.toString()
            total.text = pedidos.total.toString().plus(" €")
        }

        mesa.text = "Mesa " + getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE).getInt("id_mesa", 0)


        var pedido : List<PedidoItensFatura>

        Backend.GetItemsPedido(id_pedido.toString()){
            pedido = it
            val rv_Order : RecyclerView = findViewById(R.id.recicleFatura)
            val adapter = OrderDetailAdapter(this, pedido)

            rv_Order.adapter = adapter
            rv_Order.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        }

        procedeBtn.setOnClickListener {
            val i = Intent(this, FinishOrderActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}