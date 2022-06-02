package com.grupo1.food4u_nav.ui.admin

import Backend.addItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding
import com.grupo1.food4u_nav.databinding.FragmentDeskBinding
import com.grupo1.food4u_nav.databinding.FragmentNewItemBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu

class NewItemFragment : Fragment() {

    private var _binding: FragmentNewItemBinding? = null
    private val binding get() = _binding!!
    var categorias : List<CategoryType> = arrayListOf()
    var categoriasNome = arrayListOf<String>()

    val INITIAL_RATING = 5.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewItemBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_new_item, container, false)

        val add_foodName = view.findViewById<EditText>(R.id.add_foodName)
        val add_foodPrice = view.findViewById<EditText>(R.id.add_foodPrice)
        val add_foodTime = view.findViewById<EditText>(R.id.add_foodTime)
        val add_foodURL = view.findViewById<EditText>(R.id.add_foodUrl)
        val add_newItem = view.findViewById<MaterialButton>(R.id.add_newItem)
        val highlight = view.findViewById<Switch>(R.id.switch_highlight)


        val categoriaSpinner = view.findViewById<Spinner>(R.id.textInputLayout9)

        Backend.getAllCategories {
            categorias = it

            categoriaSpinner?.adapter = ArrayAdapter(
                activity?.applicationContext!!,
                R.layout.dropdownitem, categorias
            )
        }



        val subCategoriaSpinner = view.findViewById<Spinner>(R.id.textInputLayout10)
        val SubCategoria = arrayOf("XXX", "XXX", "XXX", "XXX", "XXX")
        subCategoriaSpinner?.adapter = ArrayAdapter(
            activity?.applicationContext!!,
            R.layout.dropdownitem, SubCategoria
        )


        add_newItem.setOnClickListener {
            var item = Item_Menu(
                null, add_foodName.text.toString(),
                add_foodPrice.text.toString().toDouble(),
                add_foodTime.text.toString().toInt(),
                highlight.isChecked, add_foodURL.text.toString(), null,
                null, 0.0
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