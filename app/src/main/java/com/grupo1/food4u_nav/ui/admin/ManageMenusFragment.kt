package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentEditItemBinding
import com.grupo1.food4u_nav.databinding.FragmentManageMenusBinding
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding


class ManageMenusFragment : Fragment() {
    private var _binding: FragmentManageMenusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentManageMenusBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_manage_menus, container, false)


        val editProducts = view.findViewById<ImageView>(R.id.products)
        val edicCategories = view.findViewById<ImageView>(R.id.manageCategories)
        val editHightlight = view.findViewById<ImageView>(R.id.manageHot)

        val backButtonManageMenus = view.findViewById<Button>(R.id.backButtonManageMenus)
        backButtonManageMenus.setOnClickListener {
            requireActivity().finish()
        }

        editProducts.setOnClickListener {
            val manager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.containerMenuManage, ShowMenu())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        edicCategories.setOnClickListener {
            val manager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.containerMenuManage, EditCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        editHightlight.setOnClickListener {
            val manager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.containerMenuManage, HighlightsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        return view
    }
}