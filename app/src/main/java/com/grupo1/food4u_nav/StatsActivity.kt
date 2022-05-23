package com.grupo1.food4u_nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.grupo1.food4u_nav.models.Cliente

class StatsActivity : AppCompatActivity() {
    var clientes: List<Cliente> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        var media : Float = 0.0F

        Backend.getAllClientes { clientes }

        for (cliente in clientes){
            media += cliente.idade!!
        }

        val mediaTextView = findViewById<TextView>(R.id.resultAge)
        mediaTextView.text = "$media"

    }
}

