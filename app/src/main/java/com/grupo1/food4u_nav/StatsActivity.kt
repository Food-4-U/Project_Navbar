package com.grupo1.food4u_nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.grupo1.food4u_nav.models.Cliente

class StatsActivity : AppCompatActivity() {
    var clientes: List<Cliente> = arrayListOf()

    inner class ClientesAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return clientes.size
        }

        override fun getItem(position: Int): Any {
            return clientes[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

            val rootView = layoutInflater.inflate(R.layout.row_cavalo,viewGroup,false)

            val result = rootView.findVu

            for (a in clientes) {

                var quantityClients = 0
                var media = 0
                if (quantityClients < clientes.size) {
                    media += clientes[a].idade
                }
            }

            return rootView
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_stats)

            val clientesAdapter = ClientesAdapter()

            Backend.getAllClientes {
                clientes = it
                clientesAdapter.notifyDataSetChanged()
            }


        }
    }
}