package com.grupo1.food4u_nav.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding

class AdditionalForm : Fragment() {
    private var _binding: FragmentAdditionFormBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdditionFormBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val fm: FragmentManager? = fragmentManager

        val backBtn: Button = root.findViewById(R.id.additionForm_backBtn)
        backBtn.setOnClickListener {
           // getFragmentManager().popBackStack(homeFragmentIdentifier, 0);

        }


        return root
    }
}