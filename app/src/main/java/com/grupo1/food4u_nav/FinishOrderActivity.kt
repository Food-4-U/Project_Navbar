package com.grupo1.food4u_nav

import Backend
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.models.data.CartItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.RatingBar


class FinishOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_order)

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

        var productName = findViewById<TextView>(R.id.finalizedOrderBottomName)
        var productPhoto = findViewById<ImageView>(R.id.imageItemView)
        val btnBack = findViewById<Button>(R.id.backBtn)
        var ratingBar = findViewById<RatingBar>(R.id.ratingBar_itensOrdem)
        var evaluationindicator = findViewById<TextView>(R.id.menu_foodEvauation3)
        var avaliacao = false

        btnBack.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                CartDatabase.getDatabase(this@FinishOrderActivity)?.cartDao()!!.deleteCart()
            }
            var observ = getSharedPreferences("Observ", Context.MODE_PRIVATE).edit().clear().apply()
            var mesa = getSharedPreferences("Mesa", MODE_PRIVATE).edit().clear().apply()

            var intent = Intent(getApplicationContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val evaluatebtn = findViewById<TextView>(R.id.evaluatebtn)

        CartDatabase.getDatabase(this).cartDao().readCart().observe(this, Observer {
            var cart = it
            var i = 0
            var nota = 0.0

            nota = Evaluate(cart[i],productName,productPhoto,ratingBar,evaluationindicator)
            i++

            evaluatebtn.setOnClickListener {
                if(i < cart.size){
                    nota =  Evaluate(cart[i],productName,productPhoto,ratingBar,evaluationindicator)
                    i++
                }
                else {

                    GlobalScope.launch(Dispatchers.IO) {
                        CartDatabase.getDatabase(this@FinishOrderActivity)?.cartDao()!!.deleteCart()
                    }
                    var observ = getSharedPreferences("Observ", Context.MODE_PRIVATE).edit().clear().apply()
                    var mesa = getSharedPreferences("Mesa", MODE_PRIVATE).edit().clear().apply()

                    var intent = Intent(getApplicationContext(), MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            }
        })

    }

    fun Evaluate (cart: CartItem,productName: TextView,productPhoto : ImageView,
                  ratingBar: RatingBar,evaluationindicator: TextView): Double{

        Backend.getItemID(cart.item_id!!){
            productName.text = it.nome
            Picasso.get().load(it.url).resize(800,650).into(productPhoto)
        }
        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                evaluationindicator.text = ratingBar.rating.toString()
            }

        ratingBar.rating = 4.50F

        return ratingBar.rating.toDouble()

    }

}

