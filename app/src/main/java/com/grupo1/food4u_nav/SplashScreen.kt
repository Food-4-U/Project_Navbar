package com.grupo1.food4u_nav

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.daimajia.androidanimations.library.Techniques
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash


class SplashScreen : AwesomeSplash() {

    override fun initSplash(configSplash: ConfigSplash?) {
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

        configSplash?.let {
            //Customize Circular Reveal
            it.backgroundColor = R.color.yellow_btn
            it.animCircularRevealDuration = 1700
            it.revealFlagX = Flags.REVEAL_RIGHT
            it.revealFlagY = Flags.REVEAL_BOTTOM

            it.logoSplash = R.drawable.logo
            it.animLogoSplashDuration = 1600
            it.animLogoSplashTechnique = Techniques.BounceInUp

            it.titleSplash = "";
            it.animTitleDuration = 0
        }

    }

    override fun animationsFinished() {
        var food4UCliente = getSharedPreferences("Cliente", MODE_PRIVATE)

        var isLogged: Boolean = food4UCliente.getBoolean("isLogged", false)
        var isAdmin: Boolean = food4UCliente.getBoolean("isAdmin", false)

        if (isLogged && isAdmin){
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
            finish()
        } else if (isLogged && !isAdmin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if(!isLogged)
        {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
