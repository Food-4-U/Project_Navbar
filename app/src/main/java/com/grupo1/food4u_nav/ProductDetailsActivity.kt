package com.grupo1.food4u_nav

import Backend
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grupo1.food4u_nav.adapters.IngredientsAdapter
import com.grupo1.food4u_nav.models.data.CartItem
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.data.CartViewModel
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
        var cartItem : CartItem = CartItem(null, null, null, null)
        lateinit var mCartViewModel: CartViewModel
        var ingredientTextView = findViewById<TextView>(R.id.textView64)
        var ingredientTextView2 = findViewById<TextView>(R.id.textView65)



        Backend.getItemID(id_item) {
            item = it

            var price = String.format("%.2f", item!!.preco)
            val url = item!!.url
            Picasso.get().load(url).resize(800,650).into(photoProduct)
            productName.text = item!!.nome
            ratingNumber.text = item!!.avaliação.toString()
            time.text = item!!.temp_prep.toString().plus(" minutos")
            priceText.text = price.plus("€")

            Backend.getNameCategory(item!!.id_categoria!!) {
                var categoryName = it.name

                categoryText.text = categoryName
            }

            Backend.getNameSubcategory(item!!.id_subcategoria!!){
                var subcategoryName = it.name

                subcategoryText.text = subcategoryName
            }

            Backend.getIngredientesByItem(item!!.id_item!!){
                var ingredients = it

                if (it.isNotEmpty()) {

                    val rv_Ingredients: RecyclerView =
                        findViewById(com.grupo1.food4u_nav.R.id.rv_ingredients)
                    val adapterIngredients = IngredientsAdapter(ingredients, this)

                    rv_Ingredients.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    rv_Ingredients.adapter = adapterIngredients
                }
                else {
                    ingredientTextView.isInvisible = true
                    ingredientTextView2.isInvisible = true
                }
            }

            var priceString : String? = null
            val quantityProduct = findViewById<TextView>(R.id.quantityProduct)
            val buttonAdd = findViewById<ImageView>(R.id.buttonAdd)
            val buttonRemove = findViewById<ImageView>(R.id.buttonRemove)
            val buttonOrder = findViewById<FloatingActionButton>(R.id.floatingActionButton)
            var qtd: Int = 1

            //Here the user can add more to his cart the quantity of the item he wants!
            // Button " + "
            buttonAdd.setOnClickListener {
                qtd += 1
                quantityProduct.text = qtd.toString()
                var price2 = qtd * item!!.preco!!
                var price = String.format("%.2f", price2)

                priceText.text = price.plus("€")
            }

            // Here he can remove, but it cant go under 1 item when pressed the remove " - " button!
            buttonRemove.setOnClickListener {
                if (qtd > 1) {
                    qtd -= 1
                    var price2 = qtd * item!!.preco!!
                    quantityProduct.text = qtd.toString()
                    var price = String.format("%.2f", price2)

                    priceText.text = price.plus("€")
                }
            }

            buttonOrder.setOnClickListener {
                cartItem.item_id = item!!.id_item
                cartItem.quantidade = qtd
                cartItem.precoTotal = qtd * item!!.preco!!


                mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
                mCartViewModel.addToCart(cartItem)

                Toast.makeText(
                    this,
                    "Adicionado ao Pedido.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val backBtn : Button = findViewById<Button>(R.id.details_backBtn)
        backBtn.setOnClickListener {
            finish()
        }

    }
}