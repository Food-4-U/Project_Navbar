package com.grupo1.food4u_nav

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.grupo1.food4u_nav.models.Cliente
import okhttp3.internal.notify
import org.w3c.dom.Text

class StatsActivity : AppCompatActivity() {
    var clientes: List<Cliente> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        supportActionBar!!.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
            windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }else{
            val flags =
                (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            window!!.decorView.systemUiVisibility = flags
        }

        var media = 0.0F
        var min = 1000
        var max = -1
        var genMasculino = 0
        var genPercentagem = 0.0F
        var mediaString : String
        var genPercentagemString : String

        Backend.getAllClientes {
            clientes = it

            for (cliente in clientes) {
                media += cliente.idade!!

                if (min > cliente.idade!! && cliente.idade!! > 0){
                    min = cliente.idade!!
                }

                if (max < cliente.idade!!){
                    max = cliente.idade!!
                }

                if (cliente.genero!!.contains('M'))
                {
                    genMasculino += 1
                }


            }

            media /= clientes.size
            mediaString = String.format("%.2f", media)

            genPercentagem = ((genMasculino.toFloat() / clientes.size) * 100)
            genPercentagemString = String.format("%.2f", genPercentagem)

            val minTextView = findViewById<TextView>(R.id.resultFloorAge)
            val maxTextView = findViewById<TextView>(R.id.resultTopAge)
            val mediaTextView = findViewById<TextView>(R.id.resultAge)
            val generoTextView = findViewById<TextView>(R.id.resultTopGender)

            mediaTextView.text = mediaString
            minTextView.text = "${min}"
            maxTextView.text = "${max}"

            if (genPercentagem > 50F)
            {
                generoTextView.text = "Masculino " + genPercentagemString + "%"
            } else
            {
                generoTextView.text = "Feminino " + genPercentagemString + "%"
            }
        }
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

