package com.grupo1.food4u_nav

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.grupo1.food4u_nav.models.Cliente
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        val textViewRegister = findViewById<TextView>(R.id.register_textbtn)

        textViewRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java);
            startActivity(intent)
            finish()
        }

        val btnLogin = findViewById<Button>(R.id.btnRegister)
        val btnGuest = findViewById<TextView>(R.id.btnLoginGuest)
        val btnRegister = findViewById<TextView>(R.id.register_textbtn)

        btnLogin.setOnClickListener {

            val editEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val editPass = findViewById<EditText>(R.id.editTextTextEmailAddress2)

            var cliente: Cliente = Cliente(email = null, id_cliente = null, password = null, nome = null)
            cliente.email = editEmail.text.toString()
            cliente.password = editPass.text.toString()

            if (cliente != null) {
                Backend.Login(cliente) {
                    if (it) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java);
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Email ou password incorreta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        btnGuest.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java);
            startActivity(intent)
            finish()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java);
            startActivity(intent)
            finish()
        }
    }

}