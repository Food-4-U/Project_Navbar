package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.EditMenuAdapter
import com.grupo1.food4u_nav.adapters.ProductMenuAdapter
import com.grupo1.food4u_nav.databinding.FragmentNewItemBinding
import com.grupo1.food4u_nav.databinding.FragmentShowMenuBinding
import com.grupo1.food4u_nav.models.Item_Menu

class ShowMenu : Fragment() {

    private var _binding: FragmentShowMenuBinding? = null
    private val binding get() = _binding!!
    var itens : List<Item_Menu> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowMenuBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_show_menu, container, false)

        val addItemBtn = view.findViewById<Button>(R.id.addItemBtn)
        addItemBtn.setOnClickListener {

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerMenuManage, NewItemFragment())
            transaction.addToBackStack("null")
            transaction.commit()
        }


        Backend.getAllItens {
            itens = it

            val rv_products = view.findViewById<RecyclerView>(R.id.rv_products)
            val productsAdapter = EditMenuAdapter(requireActivity(), itens)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter

        }

        val backButtonEditMenu = view.findViewById<Button>(R.id.backButtonEditMenu)

        backButtonEditMenu.setOnClickListener {
            getActivity()?.onBackPressed();
        }
        return view
    }
}