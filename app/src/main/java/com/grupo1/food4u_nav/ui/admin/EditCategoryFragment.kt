package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.ListViewAdapter
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterHome
import com.grupo1.food4u_nav.databinding.FragmentEditCategoryBinding
import com.grupo1.food4u_nav.databinding.FragmentNewItemBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories

class EditCategoryFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    var getCategory : List<CategoryType> = arrayListOf()
    var getSubcategory : List<SubCategories> = arrayListOf()


    var itens : List<CategoryType> = arrayListOf()

    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)
        val view =  inflater.inflate(R.layout.fragment_edit_category, container, false)


        val season1: MutableList<String> = ArrayList()
        season1.add("Winter is Coming")
        season1.add("The Kingsroad")
        season1.add("Lord Snow")

        val season2: MutableList<String> = ArrayList()
        season2.add("The North Remembers")
        season2.add("The Night Lands")
        season2.add("What is Dead May Never Die")


        header.add("Season 1")
        header.add("Season 2")

        Backend.getAllCategories {
            itens = it
            for (i in 0 until itens.count()){
                header.add(itens.toString())
            }

        }


        body.add(season1)
        body.add(season2)

        val listView : ExpandableListView = view.findViewById(R.id.expandableListView)
        listView.setAdapter(ListViewAdapter(requireActivity(),listView, header, body))


        return view
    }
}