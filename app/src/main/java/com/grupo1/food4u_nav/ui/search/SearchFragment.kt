package com.grupo1.food4u_nav.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.grupo1.food4u_nav.ItensCategoryActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.adapters.ProductMenuAdapter
import com.grupo1.food4u_nav.databinding.FragmentSearchBinding
import com.grupo1.food4u_nav.models.Item_Menu

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

        var rv = binding.rvSearch
        var imgNotFound = binding.imgNotFound
        var textNotFound = binding.textNotFound

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

        var searchView = binding.menuSearchView
        var cardview = binding.cardviewCategories

        var itens: List<Item_Menu> = arrayListOf()
        var itensPes: MutableList<Item_Menu> = ArrayList()

        rv.layoutManager = GridLayoutManager(activity, 2)

        Backend.getAllItens {
            itens = it

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    if(newText.isEmpty()){
                        rv.isGone = true
                        cardview.isGone = false
                        imgNotFound.isGone = true
                        textNotFound.isGone = true
                    }
                    else{
                        cardview.isGone = true
                        rv.isGone = false

                        itensPes.removeAll(itensPes)

                        for (element in itens)
                            if (element.nome!!.contains(newText,true))
                                itensPes.add(element)

                        if (itensPes.isEmpty())
                        {
                            imgNotFound.isGone = false
                            textNotFound.isGone = false
                            rv.isGone = true
                            cardview.isGone =true
                        }
                        else
                            rv.adapter = ProductMenuAdapter(requireActivity(),itensPes)
                    }
                    return false
                }
            })

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
