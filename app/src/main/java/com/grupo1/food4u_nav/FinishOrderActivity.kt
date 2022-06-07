package com.grupo1.food4u_nav

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.squareup.picasso.Picasso

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

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        CartDatabase.getDatabase(this).cartDao().readCart().observe(this, Observer {
            var cart = it
            Backend.getItemID(cart[0].item_id!!){
                productName.text = it.nome

                var imageURL = it.url
                Picasso.get().load(imageURL).resize(800,650).into(productPhoto)
            }
        })
    }
}