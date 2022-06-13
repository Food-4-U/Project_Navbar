package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentProductManagerBinding


class ProductManagerFragment : Fragment() {

    private var _binding: FragmentProductManagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductManagerBinding.inflate(inflater, container, false)

        var product = binding.editProduct

        product.setOnClickListener {
            val manager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(R.id.containerMenuManage, ShowMenu())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        // Inflate the layout for this fragment
        return binding.root
    }
}