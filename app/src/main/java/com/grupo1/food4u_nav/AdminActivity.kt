package com.grupo1.food4u_nav

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.grupo1.food4u_nav.ui.admin.NewItemFragment
import com.grupo1.food4u_nav.ui.home.DeskFragment

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

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


        var food4UCliente = getSharedPreferences("Cliente", MODE_PRIVATE)
        val myEdit = food4UCliente.edit()
        val btnStats = findViewById<ImageView>(R.id.statsImage)
        val btnLogOut = findViewById<Button>(R.id.btnLogout)

        btnStats.setOnClickListener {
            val intent = Intent(this@AdminActivity, StatsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogOut.setOnClickListener {
            val intent = Intent(this@AdminActivity, LoginActivity::class.java)
            food4UCliente?.edit()?.clear()?.apply();
            myEdit.putBoolean("isLogged",false)
            myEdit?.apply()
            startActivity(intent)
            finish()
        }

        val manageMenu = findViewById<ImageView>(R.id.manageMenus)

        manageMenu.setOnClickListener {
            val intent = Intent(this@AdminActivity, ManageMenusActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}