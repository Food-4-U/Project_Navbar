package com.grupo1.food4u_nav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val btnStats = findViewById<ImageView>(R.id.statsImage)

        btnStats.setOnClickListener {
            val intent = Intent(this@AdminActivity, StatsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}