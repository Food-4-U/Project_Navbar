package com.grupo1.food4u_nav

import Backend
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.net.Uri.*
import android.os.Build
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.grupo1.food4u_nav.models.Cliente
import navigation.RegisterActivity
import java.net.HttpCookie.parse
import java.net.URI
import java.net.URL
import java.util.logging.Level.parse


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        val textViewRegister = findViewById<TextView>(R.id.activityLoginRegisterButton)

        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }



        val btnLogin = findViewById<Button>(R.id.buttonRegister)
        val btnGuest = findViewById<TextView>(R.id.btnLoginGuest)
        val btnRegister = findViewById<TextView>(R.id.activityLoginRegisterButton)
        val editEmail = findViewById<EditText>(R.id.editName)
        val editPass = findViewById<EditText>(R.id.editEmail)
        editPass.transformationMethod = PasswordTransformationMethod.getInstance()

        var food4UCliente = getSharedPreferences("Cliente", MODE_PRIVATE)
        val myEdit = food4UCliente.edit()

        //COLOCAR NA SPLASH SCRREN TODO
        var isLogged: Boolean = food4UCliente.getBoolean("isLogged", false)
        var isAdmin: Boolean = food4UCliente.getBoolean("isAdmin", false)
        if (isLogged && isAdmin) {
            val intent = Intent(this@LoginActivity, AdminActivity::class.java)
            startActivity(intent)
            finish()
        } else if (isLogged && isAdmin == false) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val easter = findViewById<ImageView>(R.id.imageView3)
        var a = 0

        easter?.setOnClickListener() {
            a+= 1
            if (a == 10) {
                var url = "https://www.youtube.com/watch?v=iik25wqIuFo";
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                    a = 0
                })
            }
        }


        btnLogin.setOnClickListener {

            var cliente: Cliente = Cliente(
                email = null,
                id_cliente = null,
                password = null,
                nome = null,
                concelho = null,
                idade = null,
                genero = null,
                localidade = null,
                isAdmin = false,
                nif = null
            )
            cliente.email = editEmail.text.toString()
            cliente.password = editPass.text.toString()


            if (cliente.email.isNullOrBlank() || cliente.password.isNullOrBlank()) {
                Toast.makeText(
                    this@LoginActivity,
                    R.string.null_data,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (cliente != null) {
                Backend.Login(cliente) {
                    if (it) {
                        Backend.getClienteEmail(cliente.email!!)
                        {
                            cliente.isAdmin = it.isAdmin
                            myEdit.putString("nome", it.nome)
                            myEdit.putString("email", it.email)
                            myEdit.putString("password", it.password)
                            myEdit.putBoolean("isLogged", true)
                            myEdit.putBoolean("isAdmin", it.isAdmin) //TODO
                            myEdit.apply()

                            if (cliente.isAdmin) {
                                val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }

                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            R.string.invalid_data,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        btnGuest.setOnClickListener {
            val food4UCliente = getSharedPreferences("Cliente", MODE_PRIVATE)
            val myEdit = food4UCliente.edit()

            myEdit.putString("nome", "Convidado")
            myEdit.putString("email", "guest@food4u.pt")
            myEdit.putString("password", "123")
            myEdit.apply()

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
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