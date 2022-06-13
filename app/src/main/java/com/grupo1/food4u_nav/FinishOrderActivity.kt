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
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        var menu_foodEvauation3 = findViewById<TextView>(R.id.menu_foodEvauation3)
        var avaliacao = false
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        CartDatabase.getDatabase(this).cartDao().readCart().observe(this, Observer {
            var cart = it
            for (i in 0 until cart.size - 1) {
                Backend.getItemID(cart[i].item_id!!){
                    var a = false
                    productName.text = it.nome
                    Picasso.get().load(it.url).resize(800,650).into(productPhoto)

                    ratingBar.onRatingBarChangeListener =
                        OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                            menu_foodEvauation3.text = String.format("%.2f", rating)
                            a = true
                            GlobalScope.launch(Dispatchers.IO) {
                                CartDatabase.getDatabase(this@FinishOrderActivity)?.cartDao()!!.deleteCart()
                            }
                            var observ = getSharedPreferences("Observ", Context.MODE_PRIVATE).edit().clear().apply()
                        }
                    if (a)
                    Toast.makeText(this,"aa",Toast.LENGTH_SHORT).show()
                }

            }
            /*for (i in 0 until cart.size - 1) {
                Backend.getItemID(cart[i].item_id!!){
                    productName.text = it.nome
                    Picasso.get().load(it.url).resize(800,650).into(productPhoto)

                    if (ratingBar.rating == null)
                        menu_foodEvauation3.isGone

                   ratingBar.onRatingBarChangeListener =
                        OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                            menu_foodEvauation3.text = String.format("%.2f", rating)
                        }
                }
            }*/
        })
    }
}