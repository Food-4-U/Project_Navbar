package com.grupo1.food4u_nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.grupo1.food4u_nav.models.Cliente
import okhttp3.internal.notify

class StatsActivity : AppCompatActivity() {
    var clientes: List<Cliente> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        var media : Float = 0.0F
        //val clientesAdapter = ClientesAdapter()


        Backend.getAllClientes {
            clientes = it
            //clientesAdapter.notifyDataSetChanged()
        }

        for (cliente in clientes){
            media += cliente.idade!!
        }


        val mediaTextView = findViewById<TextView>(R.id.resultAge)
        mediaTextView.text = "${clientes.size}"

    }

    abstract inner class ClientesAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return clientes.size
        }

        override fun getItem(position: Int): Any {
            return clientes[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

    }

}

