package com.grupo1.food4u_nav.ui.admin

import Backend.addItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentDeskBinding
import com.grupo1.food4u_nav.models.Item_Menu

class NewItemFragment : Fragment() {

    private var _binding: FragmentDeskBinding? = null
    private val binding get() = _binding!!


    val INITIAL_RATING = 5.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_item, container, false)

        val add_foodName = view.findViewById<EditText>(R.id.add_foodName)
        val add_foodPrice = view.findViewById<EditText>(R.id.add_foodPrice)
        val add_foodTime = view.findViewById<EditText>(R.id.add_foodTime)
        val add_foodURL = view.findViewById<EditText>(R.id.add_foodUrl)
        val add_foodCategory = view.findViewById<EditText>(R.id.add_foodIdCategory)
        val add_foodSubCategory = view.findViewById<EditText>(R.id.add_foodIdSubCategory)
        val add_newItem = view.findViewById<MaterialButton>(R.id.add_newItem)
        val highlight = view.findViewById<Switch>(R.id.switch_highlight)

        add_newItem.setOnClickListener {
            var item = Item_Menu(
                null, add_foodName.text.toString(),
                add_foodPrice.text.toString().toDouble(),
                add_foodTime.text.toString().toInt(),
                highlight.isChecked, add_foodURL.text.toString(), add_foodCategory.text.toString().toInt(),
                add_foodSubCategory.text.toString().toInt(), 0.0
            )

            Backend.addItem(item) {
                if (it) {
                    Toast.makeText(requireActivity(), item.nome + " adicionado", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(requireActivity(), "ERRO", Toast.LENGTH_SHORT).show()
                }
            }

            /*  addItem(item) {
                  if (it)
                      Toast.makeText(requireActivity(), "funfou", Toast.LENGTH_SHORT).show()
                  else
                      Toast.makeText(requireActivity(), "nao funfou", Toast.LENGTH_SHORT).show()
              }*/
        }

        return view
    }
}