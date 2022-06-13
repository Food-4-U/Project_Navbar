package com.grupo1.food4u_nav.ui.profile.viewPager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.grupo1.food4u_nav.R.layout.fragment_wallet
import com.grupo1.food4u_nav.databinding.FragmentSettingsBinding
import com.grupo1.food4u_nav.databinding.FragmentWalletBinding
import com.grupo1.food4u_nav.models.Cliente

class Wallet : Fragment() {

    private lateinit var _binding: FragmentWalletBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs : SharedPreferences? = activity?.getSharedPreferences("Cliente",
            Context.MODE_PRIVATE
        )

        val cliente: Cliente = Cliente(email = null, id_cliente = null, password = null, nome = null, concelho = null, idade = null, genero = null, localidade = null, isAdmin = false, nif = null)
        cliente.nome = prefs?.getString("nome", "")
        cliente.email = prefs?.getString("email", "")

        val cardName = root.findViewById<TextView>(com.grupo1.food4u_nav.R.id.creditName)
        var payPalName = binding.textView29

        payPalName.text = cliente.email

        cardName.text = cliente.nome
        // Inflate the layout for this fragment
        return root
    }
}