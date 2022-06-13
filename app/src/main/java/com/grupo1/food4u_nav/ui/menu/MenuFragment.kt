package com.grupo1.food4u_nav.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.SectionAdapter
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterMenu
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SectionMenu
import com.grupo1.food4u_nav.models.SubCategories

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    var itens : List<Item_Menu> = arrayListOf()
    var subcategories : List<SubCategories> = arrayListOf()

    private var mainCategoryRecycler:RecyclerView? = null
    private var mainRecyclerAdapter: SectionAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root


        Backend.getAllSubcategories {
            subcategories = it


            val rv_subcategories : RecyclerView = root.findViewById(R.id.rv_menu)
            val subCategoriesAdapter = SubCategoriesAdapterMenu(subcategories)

            rv_subcategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            rv_subcategories.adapter = subCategoriesAdapter

            Backend.getAllItens {
                itens = it

                val allCategory: MutableList<SectionMenu> = ArrayList()

                for (i in subcategories.indices) {
                    val categoryItemList: MutableList<Item_Menu> = ArrayList()

                    for (j in itens.indices)
                        if (subcategories[i].id_SubCategory == itens[j].id_subcategoria)
                            categoryItemList.add(itens[j])

                    allCategory.add(SectionMenu(subcategories[i].name.toString(), categoryItemList))
                }

                setMainCategoryRecycler(root,allCategory)
            }
        }

        return root
    }

    private fun setMainCategoryRecycler(view:View,allCategory: List<SectionMenu>){

        mainCategoryRecycler = view.findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = SectionAdapter(requireActivity(), allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}