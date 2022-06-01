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
import com.grupo1.food4u_nav.adapters.ProductMenuAdapter
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterMenu
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import dalvik.system.BaseDexClassLoader
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var subcategories : List<SubCategories> = arrayListOf()
    var francesinhas : List<Item_Menu> = arrayListOf()
    var subCategory1 : SubCategories? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var productType1 = binding.productType1

        Backend.getNameSubcategory(1) {
            subCategory1 = it

            productType1.text = it.name
        }

        Backend.getAllSubcategories {
            subcategories = it
            val rv_subcategories : RecyclerView = root.findViewById(R.id.rv_menu)
            val subCategoriesAdapter = SubCategoriesAdapterMenu(subcategories)

            rv_subcategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            rv_subcategories.adapter = subCategoriesAdapter
        }

        Backend.getItemSubCategory(1) {
            francesinhas = it

            val rv_products : RecyclerView = binding.rvProducts
            val productsAdapter = ProductMenuAdapter(requireActivity(), francesinhas)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}