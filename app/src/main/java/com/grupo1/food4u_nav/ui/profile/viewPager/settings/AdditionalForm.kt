package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding
import com.grupo1.food4u_nav.models.Cliente
import com.grupo1.food4u_nav.models.Item_Menu
import android.widget.ArrayAdapter as ArrayAdapter

class AdditionalForm : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_addition_form, container, false)

        val prefs : SharedPreferences? = activity?.getSharedPreferences("Cliente",
            Context.MODE_PRIVATE
        )

        var id = prefs?.getInt("id", 0)

        val genero = arrayOf("Masculine", "Feminine")

        val spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, genero
        )

        val spinner2 = view.findViewById<Spinner>(R.id.spinner2)
        val age = arrayOf("10", "20", "30", "40", "50", "60", "70", "80+")
        spinner2?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, age
        )

        val spinner3 = view.findViewById<Spinner>(R.id.spinner3)
        val status = arrayOf("Braga", "Viana do Castelo", "Bragança")
        spinner3?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, status
        )

        val spinner6 = view.findViewById<Spinner>(R.id.spinner6)
        val profession = arrayOf("Guimarães", "Santo Tirso", "Caldas Das Taipas")
        spinner6?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, profession
        )

        val additionForm_backBtn = view.findViewById<Button>(R.id.additionForm_backBtn)

       /* Backend.getClientes(id!!){
            var cliente : Cliente = it
            cliente.genero = spinner.selectedItem.toString()
           // cliente.idade = spinner2.selectedItem as Int
            cliente.concelho = spinner3.selectedItem.toString()
            cliente.localidade = spinner6.selectedItem.toString()
            button2.setOnClickListener {
                Backend.updateCliente(id,cliente){
                    if (!it)
                        Toast.makeText(requireActivity(), "Falha ao atualizar", Toast.LENGTH_SHORT).show()
                }

            }
        }*/



        return view
    }
}