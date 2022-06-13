package com.grupo1.food4u_nav.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentEditCategoryBinding

class NewCategorySubFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_new_category_sub, container, false)

        val bundle = Bundle()
        bundle.putString("key", "abc")

        var radioButtonCategory = view.findViewById(R.id.radioButtonCategory) as RadioButton
        var radioButtonSub = view.findViewById(R.id.radioButtonSub) as RadioButton

        var radioGroupNewCatSub = view.findViewById(R.id.radioGroupNewCatSub) as RadioGroup
        var deleteCategorySub = view.findViewById<ImageButton>(R.id.deleteCategorySub)

        radioGroupNewCatSub.isGone = false
        deleteCategorySub.isGone = true


        var categorySubEditText = view.findViewById<EditText>(R.id.categorySubEditText)
        var submitCategorySub = view.findViewById(R.id.submitCategorySub) as TextView


        //  view.text =  getString(R.string.addCategorySub)



       return view

    }

}