package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        val addCard = binding.imageView37

        var cCardIsChecked = false
        var mbWayIsChecked = false
        var payPalIsChecked = false
        var counterIsChecked = false



        addCard.setOnClickListener{
            val activity = view!!.context as AppCompatActivity
            val myFragment: Fragment = CardFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(com.grupo1.food4u_nav.R.id.paymentMethodFragment, myFragment).addToBackStack(null).commit()
        }


        backPayButton.setOnClickListener{
            val i = Intent(activity, OrderActivity::class.java)
            startActivity(i)
        }

        cCard.setOnClickListener {
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_16)

            cCardIsChecked = true
            mbWayIsChecked = false
            payPalIsChecked = false
            counterIsChecked = false
        }

        mbWay.setOnClickListener{
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_18)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)

            cCardIsChecked = false
            mbWayIsChecked = true
            payPalIsChecked = false
            counterIsChecked = false
        }

        payPal.setOnClickListener {
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_19)

            cCardIsChecked = false
            mbWayIsChecked = false
            payPalIsChecked = true
            counterIsChecked = false
        }

        counter.setOnClickListener {
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_79)

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