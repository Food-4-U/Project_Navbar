package com.grupo1.food4u_nav
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.databinding.FragmentPaymentMethodBinding
import com.grupo1.food4u_nav.ui.admin.ShowMenu
import com.grupo1.food4u_nav.ui.profile.viewPager.settings.PaymentMethodFragment


class CompleteOrderActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_order)


    }
}