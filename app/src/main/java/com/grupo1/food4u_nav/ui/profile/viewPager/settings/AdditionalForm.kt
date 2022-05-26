package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding

class AdditionalForm : Fragment() {
    private lateinit var _binding: FragmentAdditionFormBinding




    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdditionFormBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val backBtn: Button = root.findViewById(R.id.additionForm_backBtn)
        backBtn.setOnClickListener {
            Toast.makeText(activity,"Já foste ohhhh mano!",Toast.LENGTH_LONG).show()
        }

        return root
    }

}