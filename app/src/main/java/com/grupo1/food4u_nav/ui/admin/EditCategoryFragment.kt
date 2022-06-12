package com.grupo1.food4u_nav.ui.admin

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.view.isGone
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.ListViewAdapter
import com.grupo1.food4u_nav.adapters.ListViewAdapterSub
import com.grupo1.food4u_nav.databinding.FragmentEditCategoryBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SubCategories

class EditCategoryFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    var header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<CategoryType>> = ArrayList()

    var header2: MutableList<String> = ArrayList()
    val body2: MutableList<MutableList<SubCategories>> = ArrayList()

    var categories: List<CategoryType> = arrayListOf()
    var subCategories: List<SubCategories> = arrayListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_edit_category, container, false)

        Backend.getAllCategories {
            categories = it
            header.add(getString(R.string.categories))
            body.add(categories.toMutableList())

            val listView : ExpandableListView = view.findViewById(R.id.expandableListView)
            listView.setAdapter(ListViewAdapter(requireActivity(),listView, header, body))

        }

        Backend.getAllSubcategories {
            subCategories = it
            header2.add(getString(R.string.subCategories))
            body2.add(subCategories.toMutableList())
            val listView : ExpandableListView = view.findViewById(R.id.expandableListViewSub)
            listView.setAdapter(ListViewAdapterSub(requireActivity(),listView, header2, body2))
        }

        val backButtonEditCategorySub = view.findViewById<Button>(R.id.backButtonEditCategorySub)

        backButtonEditCategorySub.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val addCategorySub = view.findViewById<Button>(R.id.addCategorySub)
        addCategorySub.setOnClickListener {
            showDialog()
        }

        return view
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_editdialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        var dialogTitle = dialog.findViewById(R.id.titleCategorySubDialog) as TextView
        var categorySubEditText = dialog.findViewById<EditText>(R.id.categorySubEditText)
        var submitCategorySub = dialog.findViewById(R.id.submitCategorySub) as TextView

        var radioButtonCategory = dialog.findViewById(R.id.radioButtonCategory) as RadioButton
        var radioButtonSub = dialog.findViewById(R.id.radioButtonSub) as RadioButton
        var radioGroupNewCatSub = dialog.findViewById(R.id.radioGroupNewCatSub) as RadioGroup
        var deleteCategorySub = dialog.findViewById<ImageButton>(R.id.deleteCategorySub)

        radioGroupNewCatSub.isGone = false
        deleteCategorySub.isGone = true

        dialogTitle.text =  getString(R.string.addCategorySub)

        submitCategorySub.setOnClickListener {
            if (categorySubEditText.text.isNullOrBlank() ) {
                Toast.makeText(
                    requireActivity(),
                    R.string.null_data,
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                if(radioButtonSub.isChecked){
                    var subCategories = SubCategories(null, categorySubEditText.text.toString(),null)
                    Backend.addSubcategory(subCategories){
                        if (!it)
                            Toast.makeText(context, "Erro ao adicionar!", Toast.LENGTH_SHORT).show()

                    }
                }else{

                    var category = CategoryType(null, categorySubEditText.text.toString(),null)
                    Backend.addCategory(category){
                        if (!it)
                            Toast.makeText(context, "Erro ao adicionar!", Toast.LENGTH_SHORT).show()
                    }
                }

                //TODO DAR REFRESH AO ADICIONAR


                dialog.dismiss()
            }
        }

        var cancelCategorySub = dialog.findViewById(R.id.cancelCategorySub) as TextView
        var closeCategorySub = dialog.findViewById(R.id.closeCategorySub) as ImageButton

        cancelCategorySub.setOnClickListener {
            dialog.dismiss()
        }
        closeCategorySub.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}