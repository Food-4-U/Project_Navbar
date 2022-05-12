package com.grupo1.food4u_nav.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.MenuAdapter
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.CategoryType
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class menuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var itens : List<CategoryType> = arrayListOf(
            CategoryType("Francesinhas", 1),
            CategoryType("Hamburgeres", 2),
            CategoryType("Saladas", 3),
            CategoryType("Snacks", 4)
        )

        val rv_menu : RecyclerView = root.findViewById(R.id.rv_menu)
        val adapter = MenuAdapter(itens)

        rv_menu.layoutManager = GridLayoutManager(activity, 2)
        rv_menu.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}