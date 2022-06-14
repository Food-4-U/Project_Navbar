package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.grupo1.food4u_nav.databinding.FragmentCardBinding
import com.grupo1.food4u_nav.models.CardNumber
import com.grupo1.food4u_nav.models.Cliente


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
        var creditCardName = binding.creditName


        val prefs : SharedPreferences? = activity?.getSharedPreferences("Cliente",
            Context.MODE_PRIVATE
        )

        val cliente: Cliente = Cliente(email = null, id_cliente = null, password = null, nome = null, concelho = null, idade = null, genero = null, localidade = null, isAdmin = false, nif = null)
        cliente.nome = prefs?.getString("nome", "")
        cliente.id_cliente = prefs?.getInt("id", 0)

        creditCardName.text = cliente.nome

        payButton.setOnClickListener {


            var cardNumber = cardNumberID.text
            var date = dateTime.text
            var cvc = cardCvc.text
            var clienteName = cardNameID.text
            var idCliente = cliente.id_cliente

            Backend.addCard(CardNumber(null, cardNumber.toString(), clienteName.toString(), date.toString(), cvc.toString().toInt(), idCliente )) {
                if (it) {
                    Toast.makeText(
                        requireActivity(),
                        "Cartão adicionado!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erro ao adicionar cartão!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return binding.root
    }

}