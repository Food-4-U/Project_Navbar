package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grupo1.food4u_nav.OrderActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentPaymentMethodBinding


class PaymentMethodFragment : Fragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)

        val backPayButton = binding.backButtonPayment

        backPayButton.setOnClickListener{
            val i = Intent(activity, OrderActivity::class.java)
            startActivity(i)
        }

        val prefs : SharedPreferences? = activity?.getSharedPreferences("Total",
            Context.MODE_PRIVATE
        )

        var totalPrice = prefs?.getString("preço", "")
        var textPrice = binding.totalPaymentMoney

        textPrice.text = totalPrice.toString()

        // Inflate the layout for this fragment
        return binding.root
    }
}