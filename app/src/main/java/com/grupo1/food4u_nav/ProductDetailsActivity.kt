package com.grupo1.food4u_nav

import android.annotation.SuppressLint
import android.media.Image
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
import androidx.fragment.app.FragmentManager
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu
import com.squareup.picasso.Picasso

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

        var item : Item_Menu? = null
        val id_item = intent.getIntExtra("id_item", 0)
        val productName = findViewById<TextView>(R.id.productName)
        val ratingNumber = findViewById<TextView>(R.id.ratingNumber)
        val photoProduct = findViewById<ImageView>(R.id.productImage)
        val time = findViewById<TextView>(R.id.timeNumber)
        val priceText = findViewById<TextView>(R.id.priceText)
        var categoryText = findViewById<TextView>(R.id.productType)
        var subcategoryText = findViewById<TextView>(R.id.productTypeName)

        Backend.getItemID(id_item) {
            item = it

            val url = item!!.url
            Picasso.get().load(url).resize(800,650).into(photoProduct)
            productName.text = item!!.nome
            ratingNumber.text = item!!.avaliação.toString()
            time.text = item!!.temp_prep.toString().plus(" minutos")
            priceText.text = item!!.preco.toString().plus("€")

            Backend.getNameCategory(item!!.id_categoria!!) {
                var categoryName = it.name

                categoryText.text = categoryName
            }

            Backend.getNameSubcategory(item!!.id_subcategoria!!){
                var subcategoryName = it.name

                subcategoryText.text = subcategoryName
            }

            var price = item!!.preco
            var priceString : String? = null
            val quantityProduct = findViewById<TextView>(R.id.quantityProduct)
            val buttonAdd = findViewById<ImageView>(R.id.buttonAdd)
            val buttonRemove = findViewById<ImageView>(R.id.buttonRemove)
            var qtd: Int = 1

            //Here the user can add more to his cart the quantity of the item he wants!
            // Button " + "
            buttonAdd.setOnClickListener {
                qtd += 1
                quantityProduct.text = qtd.toString()
                price = qtd * item!!.preco!!
                priceText.text = price.toString().plus("€")
            }

            // Here he can remove, but it cant go under 1 item when pressed the remove " - " button!
            buttonRemove.setOnClickListener {
                if (qtd > 1) {
                    qtd -= 1
                    price = qtd * item!!.preco!!
                    quantityProduct.text = qtd.toString()
                    priceText.text = price.toString().plus("€")
                }
            }
        }

        val backBtn : Button = findViewById<Button>(R.id.details_backBtn)
        backBtn.setOnClickListener {
            finish()
        }

    }
}