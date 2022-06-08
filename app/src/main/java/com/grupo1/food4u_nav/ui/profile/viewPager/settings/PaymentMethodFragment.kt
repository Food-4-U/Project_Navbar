package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val view = inflater.inflate(R.layout.fragment_payment_method, container, false)


        // Inflate the layout for this fragment
        return view
    }


}