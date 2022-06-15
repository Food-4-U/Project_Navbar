package com.grupo1.food4u_nav.ui.profile.viewPager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.FavoritesAdapter
import com.grupo1.food4u_nav.adapters.RvOrderAdapter
import com.grupo1.food4u_nav.databinding.FragmentOrderBinding
import com.grupo1.food4u_nav.databinding.FragmentWalletBinding
import com.grupo1.food4u_nav.models.Pedido

class Order : Fragment() {
    private lateinit var _binding: FragmentOrderBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rv = root.findViewById<RecyclerView>(R.id.rvOrders)

        var client = requireContext().getSharedPreferences("Cliente",
            AppCompatActivity.MODE_PRIVATE).getInt("id", 0)



        Backend.GetAllPedidos(client){
            val pedidos = it
            val adapter = RvOrderAdapter(requireActivity(),pedidos)
            var qtd = 0

            for (i in 1..pedidos.size) {
                qtd += 1
            }

            var pedidosQtd = root.findViewById<TextView>(R.id.profile_avg_evaluation)
            pedidosQtd.text = qtd.toString()

            rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            rv.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        return root
    }
}