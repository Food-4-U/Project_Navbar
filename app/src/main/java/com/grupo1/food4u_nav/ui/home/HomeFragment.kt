package com.grupo1.food4u_nav.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter
import projeto.ipca.food4u.grupoI.models.Item_Menu

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var itens : List<Item_Menu> = arrayListOf(
            Item_Menu("Hamburguer de Novilho",10,4.40F,5.80f),
            Item_Menu("Pizza á Carbonara",25,5F,13.4f),
            Item_Menu("Batatinha Frita",5,1F,4.80f),
            Item_Menu("Batatinha Frita",5,1F,4.80f),
            Item_Menu("Batatinha Frita",5,1F,4.80f),
            Item_Menu("Batatinha Frita",5,1F,4.80f)

        )

        /*
        val rv_Hottest : RecyclerView = root.findViewById(R.id.rv_hottest)
        val adapter = HottestAdapter(itens)

        rv_Hottest.layoutManager = GridLayoutManager(this, 2)
        rv_Hottest.adapter = adapter


        val product = root.findViewById<ImageView>(R.id.imageView10)


        product.setOnClickListener {
            val intent = Intent(this@Menu, ProductDetails::class.java);
            startActivity(intent)
        }
         */

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}