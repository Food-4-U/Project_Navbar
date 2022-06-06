package navigation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.grupo1.food4u_nav.LoginActivity
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Cliente

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
            window!!.decorView.setSystemUiVisibility(flags)
        }


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
        val textViewLogin = findViewById<TextView>(R.id.acitivityLoginButton)
        val btnRegister = findViewById<Button>(R.id.buttonRegister)
        val clientName = findViewById<EditText>(R.id.editName)
        val email = findViewById<EditText>(R.id.editEmail)
        val password1 = findViewById<EditText>(R.id.editPassword1)
        val password2 = findViewById<EditText>(R.id.editPassword2)
        password1.setTransformationMethod(PasswordTransformationMethod.getInstance())
        password2.setTransformationMethod(PasswordTransformationMethod.getInstance())


        textViewLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java);
            startActivity(intent)
            finish()
        }


        btnRegister.setOnClickListener {

            if (password1.text.toString() == password2.text.toString()) {
                cliente.nome = clientName.text.toString()
                cliente.email = email.text.toString()
                cliente.password = password1.text.toString()

                if (cliente.email.isNullOrBlank() || cliente.password.isNullOrBlank()) {
                    Toast.makeText(
                        this@RegisterActivity,
                        R.string.null_data,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (cliente.email!!.contains("@")) {
                        if (cliente != null) {
                            Backend.addCliente(cliente) {
                                if (it) {
                                    val food4UCliente =
                                        getSharedPreferences("Cliente", MODE_PRIVATE)
                                    val myEdit = food4UCliente.edit()

                                    myEdit.putString("nome", cliente.nome)
                                    myEdit.putString("email", cliente.email)
                                    myEdit.putString("password", cliente.password)
                                    myEdit.apply()
                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java);
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        R.string.email_existent,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            R.string.invalid_email,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}