package com.grupo1.food4u_nav.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterMenu
import com.grupo1.food4u_nav.adapters.TopRatedAdapter
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories
import com.grupo1.food4u_nav.ui.profile.viewPager.settings.AdditionalForm
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter


class   HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var subCategories : List<SubCategories> = arrayListOf(
            SubCategories(1,"Francesinha"),
            SubCategories(1,"Snacks"),
            SubCategories(1,"Massas"),
            SubCategories(1,"Kids"),
            SubCategories(1,"Bebidas")
            )

        val rv_Subcategories : RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_Subcategories)
        val adapterSubCategories = SubCategoriesAdapterMenu(subCategories)
        rv_Subcategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rv_Subcategories.adapter = adapterSubCategories


        var itens : List<Item_Menu> = arrayListOf(

            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5),
            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5),
            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5),
            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5),
            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5),
            Item_Menu(1,"Hamburguer",10F,5,null,false,2,1,4.5)
        )


        val rv_Hottest : RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_hottest)
        val adapter = HottestAdapter(itens)

        rv_Hottest.layoutManager = GridLayoutManager(activity, 2)
        rv_Hottest.adapter = adapter

        val rv_topRated: RecyclerView = root.findViewById(com.grupo1.food4u_nav.R.id.rv_topRated)
        val adapterTopRated = TopRatedAdapter(itens)

        rv_topRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rv_topRated.adapter = adapterTopRated


        val product = root.findViewById<ImageView>(com.grupo1.food4u_nav.R.id.imageView10)


        product.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java);
            startActivity(intent)
        }

        val qrCodeBtn = root.findViewById<Button>(com.grupo1.food4u_nav.R.id.QrCodeBtn)

        qrCodeBtn.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
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