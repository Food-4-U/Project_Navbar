package com.grupo1.food4u_nav.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterMenu
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.SubCategories
import dalvik.system.BaseDexClassLoader

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var subcategories : List<SubCategories> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root


        Backend.getAllSubcategories {
            subcategories = it
            val rv_menu : RecyclerView = root.findViewById(R.id.rv_menu)
            val menuAdapter = SubCategoriesAdapterMenu(subcategories)

            rv_menu.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            rv_menu.adapter = menuAdapter


        }

        val product = root.findViewById<CardView>(R.id.productCard)


        product.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java);
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}