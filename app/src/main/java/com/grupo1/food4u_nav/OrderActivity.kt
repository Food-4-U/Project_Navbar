package com.grupo1.food4u_nav

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.adapters.OrderAdapter
import com.grupo1.food4u_nav.models.data.CartViewModel

class OrderActivity : AppCompatActivity() {

    private lateinit var mCartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

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

        val back = findViewById<ImageView>(R.id.arrowBack)

        back.setOnClickListener {
            val intent = Intent(this@OrderActivity, MainActivity::class.java);
            startActivity(intent)
        }

        val rv_Order : RecyclerView = findViewById(R.id.rv_order)
        val adapter = OrderAdapter(this)

        rv_Order.adapter = adapter
        rv_Order.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        mCartViewModel.readCart.observe(this, Observer { cart ->
            adapter.setData(cart)

            val totalTextView = findViewById<TextView>(R.id.orderTotal)
            var price = adapter.getTotal().toString()
            var priceText = String.format("%.2f", price)
            totalTextView.text = priceText.plus(" €")

        })








    }
}