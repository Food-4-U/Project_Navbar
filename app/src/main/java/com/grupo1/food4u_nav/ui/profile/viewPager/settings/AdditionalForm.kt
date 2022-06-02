package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding
import android.widget.ArrayAdapter as ArrayAdapter

class AdditionalForm : Fragment() {
    private lateinit var _binding: FragmentAdditionFormBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdditionFormBinding.inflate(inflater, container, false)


        val view = inflater.inflate(R.layout.fragment_addition_form, container, false)

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
        val status = arrayOf("Single", "Meried", "Widow")
        spinner3?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, status
        )

        val spinner6 = view.findViewById<Spinner>(R.id.spinner6)
        val profession = arrayOf("Stoner", "BlackSmith", "Fire Fighter", "Nurse", "Trolha")
        spinner6?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, profession
        )

        return view
    }
}