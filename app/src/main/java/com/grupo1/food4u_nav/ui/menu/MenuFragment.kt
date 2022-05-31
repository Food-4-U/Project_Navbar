package com.grupo1.food4u_nav.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.MenuAdapter
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    var francesinhas : List<Item_Menu> = arrayListOf()
    var cachorros : List<Item_Menu> = arrayListOf()
    var hamburgueres : List<Item_Menu> = arrayListOf()
    var pregos : List<Item_Menu> = arrayListOf()
    var vegan : List<Item_Menu> = arrayListOf()
    var glutenFree : List<Item_Menu> = arrayListOf()

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

        val categories : List<CategoryType> = arrayListOf(
            CategoryType(1, "Francesinhas"),
            CategoryType(2, "Hamburgueres"),
            CategoryType(3, "Saladas"),
            CategoryType(4, "Snacks")
        )

        Backend.getItemSubCategory(1) {
            francesinhas = it

            val rv_product : RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_products)
            val adapter = MenuAdapter(requireActivity(), francesinhas)

            rv_product.layoutManager = GridLayoutManager(activity, 2)
            rv_product.adapter = adapter
        }

        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}