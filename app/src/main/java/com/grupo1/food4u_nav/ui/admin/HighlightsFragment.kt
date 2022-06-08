package com.grupo1.food4u_nav.ui.admin

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.EditMenuAdapter
import com.grupo1.food4u_nav.adapters.HighlightEditAdapter
import com.grupo1.food4u_nav.models.Item_Menu

class HighlightsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_highlights, container, false)

        var itens : List<Item_Menu> = emptyList()

        val backBtn = view.findViewById<ImageButton>(R.id.backBtnEditIHighLight)

        backBtn.setOnClickListener{
            fragmentManager?.popBackStack()
        }

        Backend.getItemTop {
            itens = it
            val rv_products = view.findViewById<RecyclerView>(R.id.rv_highlightEdit)
            val productsAdapter = HighlightEditAdapter(requireActivity(), itens)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter

        }

        return view
    }
}


