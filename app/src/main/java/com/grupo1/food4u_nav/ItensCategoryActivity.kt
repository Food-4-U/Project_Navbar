package com.grupo1.food4u_nav

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.models.Item_Menu
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class ItensCategoryActivity : AppCompatActivity() {

    var itens : List<Item_Menu> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itens_category)

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

        var category = findViewById<TextView>(R.id.itemCategory)
        var id_category = intent.getIntExtra(CATEGORY_ID, 0)
        var categoryName = intent.getStringExtra(CATEGORY_NAME)

        val btnBack = findViewById<Button>(R.id.backButton)

        category.text = categoryName

        Backend.getItemCategory(id_category) {
            itens = it.sortedBy { it.id_subcategoria }

            val rv_Hottest : RecyclerView = findViewById(R.id.rv_itensCategory)
            val adapter = HottestAdapter(this, itens)

            rv_Hottest.layoutManager = GridLayoutManager(this, 2)
            rv_Hottest.adapter = adapter
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val CATEGORY_ID = "id_category"
        const val CATEGORY_NAME = "categoryName"
    }
}
