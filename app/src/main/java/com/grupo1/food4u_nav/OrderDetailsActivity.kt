package com.grupo1.food4u_nav

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.adapters.OrderAdapter
import com.grupo1.food4u_nav.adapters.OrderDetailAdapter
import com.grupo1.food4u_nav.models.PedidoItensFatura
import org.w3c.dom.Text

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController =
                ViewCompat.getWindowInsetsController(window.decorView) ?: return
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        } else {
            val flags =
                (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            window!!.decorView.systemUiVisibility = flags
        }

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
        val tipoPayment = findViewById<TextView>(R.id.pagamentoFatura)

        var id_pedido = intent.getIntExtra("id_pedido", 0)
        var id_cliente = getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)
        tipoPayment.text = getSharedPreferences("Tipo", MODE_PRIVATE).getString("tipo", "")

        Backend.getClientes(id_cliente){
            var cliente = it

            nomeClient.text = cliente.nome
            nif.text = getSharedPreferences("Nif", MODE_PRIVATE).getString("nif", "")
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