package com.grupo1.food4u_nav

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.ui.admin.*

class ManageMenusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_menus)

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


         val editProducts = findViewById<ImageView>(R.id.addProduct)
         val edicCategories = findViewById<ImageView>(R.id.manageCategories)
        val editHightlight = findViewById<ImageView>(R.id.manageHot)


          editProducts.setOnClickListener {
              val manager: FragmentManager = supportFragmentManager
              val transaction: FragmentTransaction = manager.beginTransaction()
              transaction.add(R.id.containerMenuManage, ShowMenu())
              transaction.addToBackStack(null)
              transaction.commit()
          }


        edicCategories.setOnClickListener {
              val manager: FragmentManager = supportFragmentManager
              val transaction: FragmentTransaction = manager.beginTransaction()
              transaction.add(R.id.containerMenuManage, EditCategoryFragment())
              transaction.addToBackStack(null)
              transaction.commit()
          }


        editHightlight.setOnClickListener {
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(R.id.containerMenuManage, HighlightsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}