package com.grupo1.food4u_nav.ui.menu

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.ProductMenuAdapter
import com.grupo1.food4u_nav.adapters.SectionAdapter
import com.grupo1.food4u_nav.adapters.SubCategoriesAdapterMenu
import com.grupo1.food4u_nav.databinding.FragmentMenuBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.Section
import com.grupo1.food4u_nav.models.SubCategories
import dalvik.system.BaseDexClassLoader
import okhttp3.internal.concurrent.TaskRunner
import projeto.ipca.food4u.grupoI.adapters.HottestAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    /*var subcategories : List<SubCategories> = arrayListOf()
    var francesinhas : List<Item_Menu> = arrayListOf()
    var hamburgueres : List<Item_Menu> = arrayListOf()
    var cachorros : List<Item_Menu> = arrayListOf()
    var pregos : List<Item_Menu> = arrayListOf()*/
    var subCategory1 : SubCategories? = null
    var subCategory2 : SubCategories? = null
    var subCategory3 : SubCategories? = null
    var subCategory4 : SubCategories?= null


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


   /*    Backend.getAllSubcategories {
            categories = it

        }*/

      /*  var productType1 = binding.productType1
        var productType2 = binding.productType2
        var productType3 = binding.productType3
        var productType4 = binding.productType4

        Backend.getNameSubcategory(1) {
            if (it.name == null){
                AlertDialog.Builder(requireActivity())
                    .setTitle("Alerta Conexão Internet")
                    .setMessage("Por favor verifique a sua conexão à Internet")
                    .setPositiveButton(
                        "Fechar"
                    ) { dialogInterface, i -> requireActivity().finish() }.show()
            }else{
                subCategory1 = it

                productType1.text = it.name
            }
        }

        Backend.getNameSubcategory(2) {
            subCategory2 = it

            productType2.text = it.name
        }

        Backend.getNameSubcategory(3) {
            subCategory3 = it

            productType3.text = it.name
        }

        Backend.getNameSubcategory(7) {
            subCategory4 = it
            productType4.text = it.name
        }*/

        Backend.getAllSubcategories {
            subcategories = it
            val rv_subcategories : RecyclerView = root.findViewById(R.id.rv_menu)
            val subCategoriesAdapter = SubCategoriesAdapterMenu(subcategories)

            rv_subcategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            rv_subcategories.adapter = subCategoriesAdapter
            Backend.getAllItens {
                itens = it

                val allCategory: MutableList<Section> = ArrayList()

                for (i in subcategories.indices) {
                    val categoryItemList: MutableList<Item_Menu> = ArrayList()

                    for (j in itens.indices)
                        if (subcategories[i].id_SubCategory == itens[j].id_subcategoria)
                            categoryItemList.add(itens[j])

                    allCategory.add(Section(subcategories[i].name.toString(), categoryItemList))
                }

                setMainCategoryRecycler(root,allCategory)
            }
        }


        /*

        Backend.getItemSubCategory(1) {
            francesinhas = it

            val rv_products : RecyclerView = binding.rvProducts1
            val productsAdapter = ProductMenuAdapter(requireActivity(), francesinhas)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter
        }

        Backend.getItemSubCategory(2) {
            hamburgueres = it

            val rv_products : RecyclerView = binding.rvProducts2
            val productsAdapter = ProductMenuAdapter(requireActivity(), hamburgueres)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter
        }

        Backend.getItemSubCategory(3) {
            cachorros = it

            val rv_products : RecyclerView = binding.rvProducts3
            val productsAdapter = ProductMenuAdapter(requireActivity(), cachorros)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter
        }

        Backend.getItemSubCategory(7) {
            pregos = it

            val rv_products : RecyclerView = binding.rvProducts4
            val productsAdapter = ProductMenuAdapter(requireActivity(), pregos)

            rv_products.layoutManager = GridLayoutManager(activity, 2)
            rv_products.adapter = productsAdapter

        }*/

        return root
    }

    private fun setMainCategoryRecycler(view:View,allCategory: List<Section>){

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