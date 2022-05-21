package com.grupo1.food4u_nav.ui.profile.viewPager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.databinding.FragmentSettingsBinding
import com.grupo1.food4u_nav.ui.profile.viewPager.settings.AdditionalForm


class Settings : Fragment() {

    private lateinit var _binding: FragmentSettingsBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnForm : TextView = root.findViewById(com.grupo1.food4u_nav.R.id.profile_additionalForm)


        btnForm.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(com.grupo1.food4u_nav.R.id.container, AdditionalForm())
            fragmentTransaction.addToBackStack("null").commit()
        }

        return root
    }
}