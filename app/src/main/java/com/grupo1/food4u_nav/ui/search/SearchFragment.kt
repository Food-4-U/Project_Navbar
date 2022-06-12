package com.grupo1.food4u_nav.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.CategoriesAdapter
import com.grupo1.food4u_nav.adapters.ProductMenuAdapter
import com.grupo1.food4u_nav.databinding.FragmentSearchBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    var categories: List<CategoryType> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Backend.getAllCategories {
            categories = it

            val rv_Categories : RecyclerView = root.findViewById(R.id.rv_categories)
            val CategoriesAdapter = CategoriesAdapter(requireActivity(),categories)

            rv_Categories.layoutManager = GridLayoutManager(activity, 2)
            rv_Categories.adapter = CategoriesAdapter
        }

        var rv = binding.rvSearch
        var imgNotFound = binding.imgNotFound
        var textNotFound = binding.textNotFound


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
