package com.grupo1.food4u_nav.ui.profile.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.FavoritesAdapter
import com.grupo1.food4u_nav.databinding.FragmentFavoritesBinding
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import com.grupo1.food4u_nav.databinding.FragmentProfileBinding
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter
import projeto.ipca.food4u.grupoI.models.Item_Menu

class Favorites : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        //    val root: View = binding.root

        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)

        var itens : List<Item_Menu> = arrayListOf(
            Item_Menu("Hamburguer",10,4.40F,5.80f),
            Item_Menu("Hamburguer",10,4.40F,5.80f),
            Item_Menu("Hamburguer",10,4.40F,5.80f),
            Item_Menu("Hamburguer",10,4.40F,5.80f),
            Item_Menu("Hamburguer",10,4.40F,5.80f)
        )

        var rv_Favorites : RecyclerView = view.findViewById(R.id.rv_favorites)
        val adapter = FavoritesAdapter(itens)

        rv_Favorites.layoutManager = GridLayoutManager(context, 2)
        rv_Favorites.adapter = adapter

        adapter.notifyDataSetChanged()

        return view
    }
}