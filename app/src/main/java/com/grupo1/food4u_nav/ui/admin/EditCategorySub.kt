package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentEditCategorySubBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.SubCategories


class EditCategorySub : Fragment() {


    private var _binding: FragmentEditCategorySubBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditCategorySubBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_edit_category_sub, container, false)


        var namePageEditCategory = view!!.findViewById<TextView>(R.id.namePageEditCategory)
        var categorySubEditText = view!!.findViewById<EditText>(R.id.categorySubAddText2)
        var categorySubUrl = view!!.findViewById<EditText>(R.id.categorySubAddUrl)
        var submitCategorySub = view!!.findViewById(R.id.edit_categorySub) as MaterialButton


        val getData = arguments
        var array = getData!!.getStringArray("data")

        if (array!![0].toInt() == 0)
            namePageEditCategory.text = getString(R.string.editCategory)
        else
            namePageEditCategory.text = getString(R.string.editSubCategory)

        categorySubEditText.setText(array!![2].toString())
        categorySubUrl.setText(array!![3].toString())

        var backButtonEditCategorySub3 = view!!.findViewById<Button>(R.id.backButtonEditCategorySub3)
        backButtonEditCategorySub3.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        var deleteCategorySub = view!!.findViewById<ImageButton>(R.id.deleteCategorySub2)
        deleteCategorySub.setOnClickListener {
            if (array!![0].toInt() == 0)
                Backend.deleteCategory(array!![1].toInt()){
                    if (!it)
                        Toast.makeText(context,"Erro ao remover!", Toast.LENGTH_SHORT).show()
                }
            else
                Backend.deleteSubcategory(array!![1].toInt()){
                    if (!it)
                        Toast.makeText(context,"Erro ao remover!", Toast.LENGTH_SHORT).show()
                }

            goBack()
        }

        submitCategorySub.setOnClickListener {
            if (categorySubEditText.text.isNullOrBlank() || categorySubUrl.text.isNullOrBlank()) {
                Toast.makeText(
                    requireActivity(),
                    R.string.null_data,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (array!![0].toInt() == 0) {
                    var category = CategoryType(
                        null,
                        categorySubEditText.text.toString(),
                        categorySubUrl.text.toString()
                    )
                    Backend.updateCategory(array!![1].toInt(), category) {
                        if (!it)
                            Toast.makeText(context, "Erro ao editar!", Toast.LENGTH_SHORT)
                                .show()
                    }
                } else {
                    var subCategories = SubCategories(
                        null,
                        categorySubEditText.text.toString(),
                        categorySubUrl.text.toString()
                    )
                    Backend.updateSubcategory(array!![1].toInt(), subCategories) {
                        if (!it)
                            Toast.makeText(context, "Erro ao editar!", Toast.LENGTH_SHORT)
                                .show()
                    }
                }

                fragmentManager?.popBackStack()//goBack()
            }
        }
        return view
    }

    fun goBack(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerMenuManage, CategorySubFragment())
        transaction.commit()
    }
}