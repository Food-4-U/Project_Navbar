package com.grupo1.food4u_nav.ui.home

import Backend
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterHome
import com.grupo1.food4u_nav.adapters.TopRatedAdapter
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter


class HomeFragment : Fragment() {

    var itens : List<Item_Menu> = arrayListOf()
    var subCategories : List<SubCategories> = arrayListOf()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Backend.getAllSubcategories {
            subCategories = it

            val rv_Subcategories : RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_Subcategories)
            val adapterSubCategories = SubCategoriesAdapterHome(subCategories)
            rv_Subcategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            rv_Subcategories.adapter = adapterSubCategories
        }

        Backend.getItemTop {
            itens = it

            val rv_Hottest : RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_hottest)
            val adapter = HottestAdapter(requireActivity(),itens)

            rv_Hottest.layoutManager = GridLayoutManager(activity, 2)
            rv_Hottest.adapter = adapter

            itens = it.sortedByDescending { it.avaliação }

            val rv_topRated: RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_topRated)
            val adapterTopRated = TopRatedAdapter(requireActivity(),itens)

            rv_topRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            rv_topRated.adapter = adapterTopRated
        }

        val qrCodeBtn = root.findViewById<Button>(com.grupo1.food4u_nav.R.id.QrCodeBtn)

        qrCodeBtn.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(com.blogspot.atifsoftwares.animatoolib.R.anim.animate_slide_in_left, com.blogspot.atifsoftwares.animatoolib.R.anim.animate_slide_out_right)
            fragmentTransaction.replace(com.grupo1.food4u_nav.R.id.container, DeskFragment())
            fragmentTransaction.addToBackStack("null").commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}