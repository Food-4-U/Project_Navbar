package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentEditCategoryBinding
import com.grupo1.food4u_nav.databinding.FragmentEditItemBinding
import com.grupo1.food4u_nav.databinding.FragmentNewCategorySubBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.SubCategories

class NewCategorySubFragment : Fragment() {

    private var _binding: FragmentNewCategorySubBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewCategorySubBinding.inflate(inflater, container, false)
        val view =  inflater.inflate(R.layout.fragment_new_category_sub, container, false)

        var radioButtonCategory = view.findViewById(R.id.radioButtonCategory) as RadioButton


        var categorySubAddText2 = view.findViewById<EditText>(R.id.categorySubEditText3)
        var categorySubAddUrl = view.findViewById<EditText>(R.id.categorySubEditUrl2)
        var add_categorySub2 = view.findViewById<MaterialButton>(R.id.add_categorySub2)

        var backBtnAdd = view.findViewById<Button>(R.id.backBtnAdd)

        backBtnAdd.setOnClickListener {
            fragmentManager!!.popBackStack()
        }


        add_categorySub2.setOnClickListener {
            var name = categorySubAddText2.text.toString()
            var url = categorySubAddUrl.text.toString()

            if (name.isNullOrBlank() || url.isNullOrBlank()){
                Toast.makeText(
                    requireActivity(),
                    R.string.null_data,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                if (radioButtonCategory.isChecked)
                    Backend.addCategory(CategoryType(null,name,url)){
                        if (!it)
                            Toast.makeText(requireActivity(),"Falha ao adicionar",Toast.LENGTH_SHORT).show()
                    }
                else
                    Backend.addSubcategory(SubCategories(null,name,url)){
                        if (!it)
                            Toast.makeText(requireActivity(),"Falha ao adicionar",Toast.LENGTH_SHORT).show()
                    }

                goBack()
            }
        }
        return view
    }


    fun goBack(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerMenuManage, CategorySubFragment())
        transaction.commit()
        fragmentManager!!.popBackStack()
    }

}