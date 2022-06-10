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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.grupo1.food4u_nav.FinishOrderActivity
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
        val payPal = binding.imageView29
        val counter = binding.imageView32
        val payButton = binding.continueOrder

        var cCardIsChecked = false
        var mbWayIsChecked = false
        var payPalIsChecked = false
        var counterIsChecked = false



        backPayButton.setOnClickListener{
            val i = Intent(activity, OrderActivity::class.java)
            startActivity(i)
        }

        cCard.setOnClickListener {
            mbWay.setImageResource(R.drawable.grupo_15)
            payPal.setImageResource(R.drawable.grupo_14)
            counter.setImageResource(R.drawable.grupo_11)
            cCard.setImageResource(R.drawable.grupo_16)

            cCardIsChecked = true
            mbWayIsChecked = false
            payPalIsChecked = false
            counterIsChecked = false
        }

        mbWay.setOnClickListener{
            cCard.setImageResource(R.drawable.grupo_80)
            mbWay.setImageResource(R.drawable.grupo_18)
            counter.setImageResource(R.drawable.grupo_11)
            payPal.setImageResource(R.drawable.grupo_14)

            cCardIsChecked = false
            mbWayIsChecked = true
            payPalIsChecked = false
            counterIsChecked = false
        }

        payPal.setOnClickListener {
            cCard.setImageResource(R.drawable.grupo_80)
            mbWay.setImageResource(R.drawable.grupo_15)
            counter.setImageResource(R.drawable.grupo_11)
            payPal.setImageResource(R.drawable.grupo_19)

            cCardIsChecked = false
            mbWayIsChecked = false
            payPalIsChecked = true
            counterIsChecked = false
        }

        counter.setOnClickListener {
            cCard.setImageResource(R.drawable.grupo_80)
            mbWay.setImageResource(R.drawable.grupo_15)
            payPal.setImageResource(R.drawable.grupo_14)
            counter.setImageResource(R.drawable.grupo_79)

            cCardIsChecked = false
            mbWayIsChecked = false
            payPalIsChecked = false
            counterIsChecked = true
        }


        payButton.setOnClickListener {
            if (cCardIsChecked) {

            }

            else if (mbWayIsChecked) {

            }

            else if (payPalIsChecked) {

            }

            else if (counterIsChecked) {
                Toast.makeText(
                    requireActivity(),
                    "Aguarde Funcionário.",
                    Toast.LENGTH_SHORT
                ).show()

                val i = Intent(activity, FinishOrderActivity::class.java)
                startActivity(i)
            }
            else {
                Toast.makeText(
                    requireActivity(),
                    "Por favor selecione uma opção.",
                    Toast.LENGTH_SHORT
                ).show()
            }
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