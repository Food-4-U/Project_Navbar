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

    var itens: List<CategoryType> = arrayListOf()
    var itensSub: List<SubCategories> = arrayListOf()
    var itensMenu: List<Item_Menu> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_edit_category, container, false)


        Backend.getAllCategories {
            itens = it

            var header: MutableList<String> = ArrayList()
            val body: MutableList<MutableList<String>> = ArrayList()

            Backend.getAllItens {
                itensMenu = it

                for (i in 0 until itens.size) {
                    header.add(itens[i].name)

                    var sub: MutableList<String> = ArrayList()
                    for (j in 0 until itensMenu.size){
                        if (itensMenu[j].id_categoria == itens[i].id){
                            sub.add(itensMenu[j].nome.toString())
                        }
                    }

                    body.add(sub)
                }

                val listView : ExpandableListView = view.findViewById(R.id.expandableListView)
                listView.setAdapter(ListViewAdapter(requireActivity(),listView, header, body))
                for (i in 0 until itens.size) {
                    listView.expandGroup(i,true)
                }

            }





        }
        return view
    }
}