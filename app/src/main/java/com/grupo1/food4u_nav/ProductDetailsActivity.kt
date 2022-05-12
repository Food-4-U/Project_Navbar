package com.grupo1.food4u_nav

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class ProductDetailsActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        supportActionBar!!.hide()

        // This function make that the app always run in fullscreen!
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

        val quantityProduct = findViewById<TextView>(R.id.quantityProduct)
        val buttonAdd = findViewById<ImageView>(R.id.buttonAdd)
        val buttonRemove = findViewById<ImageView>(R.id.buttonRemove)
        var qtd: Int = 1
        var priceText = findViewById<TextView>(R.id.priceText)

        //Here the user can add more to his cart the quantity of the item he wants!
        // Button " + "
        buttonAdd.setOnClickListener {
            var price = 7.5
            qtd += 1
            quantityProduct.text = qtd.toString()
            price = qtd * price
            priceText.text = price.toString().plus("€")
        }

        // Here he can remove, but it cant go under 1 item when pressed the remove " - " button!
        buttonRemove.setOnClickListener {
            var price = 7.5
            if (qtd > 1) {
                qtd -= 1
                price *= qtd
                quantityProduct.text = qtd.toString()
                priceText.text = price.toString().plus("€")
            }
        }
    }
}