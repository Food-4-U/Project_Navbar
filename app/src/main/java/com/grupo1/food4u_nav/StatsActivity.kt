package com.grupo1.food4u_nav

import Backend
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.grupo1.food4u_nav.models.Cliente
import com.grupo1.food4u_nav.models.Pedido
import java.text.SimpleDateFormat
import java.util.*

class StatsActivity : AppCompatActivity() {
    var clientes: List<Cliente> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

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

        var media = 0.0F
        var min = 1000
        var max = -1
        var genMasculino = 0
        var genFem = 0
        var genPercentagem = 0.0F
        var mediaString: String
        var genPercentagemString: String

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)

        Backend.getAllClientes {
            if (it.isNullOrEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Alerta Conexão Internet")
                    .setMessage("Por favor verifique a sua conexão à Internet")
                    .setPositiveButton(
                        "Fechar"
                    ) { dialogInterface, i -> finish() }.show()
            } else {
                clientes = it

                for (cliente in clientes) {
                    media += cliente.idade!!

                    if (min > cliente.idade!! && cliente.idade!! > 0) {
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
                genPercentagemString = String.format("%.2f", genPercentagem)

                val aaChartModel: AAChartModel = AAChartModel()
                    .chartType(AAChartType.Bar)
                    .title("Gender")
                    .backgroundColor("#FFFFFF")
                    .dataLabelsEnabled(true)
                    .series(
                        arrayOf(
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

                if (genPercentagem > 50F) {
                    generoTextView.text = "Masculino " + genPercentagemString + "%"
                } else {
                    generoTextView.text = "Feminino " + genPercentagemString + "%"
                }
            }
        }

        Backend.GetCountPedidoGenero("feminino") {
            var qtdFem = 0
            var qtdMasc = 0
            qtdFem = it
            Backend.GetCountPedidoGenero("masculino") {
                qtdMasc = it
                val aaChartView = findViewById<AAChartView>(R.id.graphOr)

                val countOrderByGender: AAChartModel = AAChartModel()
                    .chartType(AAChartType.Waterfall)
                    .title("Orders")
                    .backgroundColor("#FFFFFF")
                    .dataLabelsEnabled(false)
                    .series(
                        arrayOf(
                            AASeriesElement()
                                .name("Feminino")
                                .data(arrayOf(qtdFem)),
                            AASeriesElement()
                                .name("Masculino")
                                .data(arrayOf(qtdMasc))
                        )
                    )
                aaChartView.aa_drawChartWithChartModel(countOrderByGender)
            }

        }


        val Total = findViewById<TextView>(R.id.resultTopGender2)
        val mediaTotal = findViewById<TextView>(R.id.resultTopGender3)
        val mediaM = findViewById<TextView>(R.id.resultTopGender4)
        val mediaF = findViewById<TextView>(R.id.resultTopGender5)
        val Desv = findViewById<TextView>(R.id.resultTopGender6)


        Backend.GetAvgPedido {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText = medianText.plus(" €")
            mediaTotal.text = medianText
        }

        Backend.GetAvgPedidoGenero("Masculino") {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText = medianText.plus(" €")
            mediaM.text = medianText
        }

        Backend.GetAvgPedidoGenero("Feminino") {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText = medianText.plus(" €")
            mediaF.text = medianText
        }

        Backend.GetTotalPedido() {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText = medianText.plus(" €")
            Total.text = medianText
        }

        Backend.GetDesvPedido() {
            var median = it
            var medianText = String.format("%.2f", median)
            medianText = medianText.plus(" €")
            Desv.text = medianText
        }
    }
}