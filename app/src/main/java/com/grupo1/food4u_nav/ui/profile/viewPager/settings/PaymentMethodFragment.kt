package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
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
        val cCard = binding.imageView25
        val mbWay = binding.imageView27
        val payPay = binding.imageView29
        val counter = binding.imageView32



        backPayButton.setOnClickListener{
            val i = Intent(activity, OrderActivity::class.java)
            startActivity(i)
        }

        cCard.setOnClickListener{

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