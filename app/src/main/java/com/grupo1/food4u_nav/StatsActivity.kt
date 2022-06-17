package com.grupo1.food4u_nav

import android.app.AlertDialog
import android.content.res.Resources
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
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
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
        var genFem = 0
        var genPercentagem = 0.0F
        var mediaString : String
        var genPercentagemString : String

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)

        Backend.getAllClientes {
            if (it.isNullOrEmpty())
            {
                AlertDialog.Builder(this)
                    .setTitle("Alerta Conexão Internet")
                    .setMessage("Por favor verifique a sua conexão à Internet")
                    .setPositiveButton(
                        "Fechar"
                    ) { dialogInterface, i -> finish() }.show()
            }else
            {

                clientes = it

                for (cliente in clientes) {
                    media += cliente.idade!!

                    if (min > cliente.idade!! && cliente.idade!! > 0){
                        min = cliente.idade!!
                    }

                    if (max < cliente.idade!!)
                        max = cliente.idade!!

                    if (cliente.genero!!.contains('M'))
                        genMasculino += 1

                    if (cliente.genero!!.contains('F'))
                        genFem++

                }



                media /= clientes.size
                mediaString = String.format("%.2f", media)

                genPercentagem = ((genMasculino.toFloat() / clientes.size) * 100)
                genPercentagemString = String. format("%.2f", genPercentagem)

                val aaChartModel : AAChartModel = AAChartModel()
                    .chartType(AAChartType.Bar)
                    .title("Gender")
                    .backgroundColor("#FFFFFF")
                    .dataLabelsEnabled(true)
                    .series(arrayOf(
                        AASeriesElement()
                            .name("Feminino")
                            .data(arrayOf(genFem)),
                        AASeriesElement()
                            .name("Masculino")
                            .data(arrayOf(genMasculino))

                    )
                    )

                aaChartView.aa_drawChartWithChartModel(aaChartModel)

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

        val mediaPedido = findViewById<TextView>(R.id.resultTopGender2)
        val mediaM = findViewById<TextView>(R.id.resultTopGender3)
        val mediaF = findViewById<TextView>(R.id.resultTopGender4)

        Backend.GetAvgPedido {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText =  medianText.plus(" €")
            mediaPedido.text = medianText
        }

        Backend.GetAvgPedidoGenero("Masculino") {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText =  medianText.plus(" €")
            mediaM.text = medianText
        }

        Backend.GetAvgPedidoGenero("Feminino") {
            var median = it
            var medianText= String.format("%.2f", median)
            medianText =  medianText.plus(" €")
            mediaF.text = medianText
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

