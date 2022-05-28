package com.grupo1.food4u_nav.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.grupo1.food4u_nav.ItensCategoryActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var id_category : Int = 0
        var categoryName : String? = null
        var entryPhoto = binding.entryImage
        var mainDishImage = binding.mainDishImage
        var drinksImage = binding.drinksImage
        var alcoholicsImage = binding.imageView22
        var extrasImage = binding.imageView26
        var desertImage = binding.imageView28
        var glutenFreeImage = binding.imageView30
        var veganImage = binding.imageView31

        entryPhoto.setOnClickListener {
            id_category = 1
            categoryName = "Entradas"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        mainDishImage.setOnClickListener {
            id_category = 2
            categoryName = "Pratos Principais"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        drinksImage.setOnClickListener {
            id_category = 3
            categoryName = "Bebidas"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        alcoholicsImage.setOnClickListener {
            id_category = 4
            categoryName = "Bebidas Alcoólicas"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        extrasImage.setOnClickListener {
            id_category = 5
            categoryName = "Acompanhamentos"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        desertImage.setOnClickListener {
            id_category = 6
            categoryName = "Sobremesas"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        glutenFreeImage.setOnClickListener {
            id_category = 7
            categoryName = "Glúten Free"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        veganImage.setOnClickListener {
            id_category = 8
            categoryName = "Vegan"
            val intent = Intent(activity, ItensCategoryActivity::class.java)
            intent.putExtra(ItensCategoryActivity.CATEGORY_ID, id_category)
            intent.putExtra(ItensCategoryActivity.CATEGORY_NAME, categoryName)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}