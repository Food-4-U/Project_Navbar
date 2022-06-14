package com.grupo1.food4u_nav.ui.profile.viewPager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.FavoritesAdapter
import com.grupo1.food4u_nav.adapters.RvOrderAdapter
import com.grupo1.food4u_nav.models.Pedido

class Order : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rvOrders)

        var client = requireContext().getSharedPreferences("Cliente",
            AppCompatActivity.MODE_PRIVATE).getInt("id", 0)

        Backend.GetAllPedidos(client){
            val pedidos = it
            val adapter = RvOrderAdapter(requireActivity(),pedidos)

            rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
            rv.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        return view
    }
}