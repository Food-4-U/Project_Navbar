package com.grupo1.food4u_nav.ui.profile.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButtonToggleGroup
import com.grupo1.food4u_nav.R

class Order : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val toggleButtonGroup: MaterialButtonToggleGroup = view.findViewById(R.id.toggleButtonGroup)
        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            if (isChecked) {
                when (checkedId) {
                    R.id.button5 -> Toast.makeText(activity,"oi",Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }
}