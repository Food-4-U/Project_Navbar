package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding
import com.grupo1.food4u_nav.databinding.FragmentCardBinding


class CardFragment : Fragment() {

    private lateinit var _binding: FragmentCardBinding
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCardBinding.inflate(inflater, container, false)


        val backBtn = binding.backBtnCardFragment
        val cardNumber = binding.textView9
        val cardName = binding.cardName
        val cardDate = binding.textView24
        val cvc = binding.textView26
        val cardNumberID = binding.editTextTextPersonName2
        val dateTime = binding.editTextTextPersonName3
        val cardCvc = binding.cvc
        val cardNameID = binding.editTextTextPersonName4
        val payButton = binding.cardPayButton




        return binding.root
    }

}