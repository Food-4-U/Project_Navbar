package com.grupo1.food4u_nav.ui.admin

import Backend.addItem
import android.content.ClipData
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentAdditionFormBinding
import com.grupo1.food4u_nav.databinding.FragmentDeskBinding
import com.grupo1.food4u_nav.databinding.FragmentNewItemBinding
import com.grupo1.food4u_nav.models.CategoryType
import com.grupo1.food4u_nav.models.Item_Menu

@Suppress("DEPRECATION")
class EditItemFragment : Fragment() {

    private var _binding: FragmentNewItemBinding? = null
    private val binding get() = _binding!!
    var categorias: List<String> = arrayListOf()
    var subcategorias: List<String> = arrayListOf()
    var item: Item_Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewItemBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_edit_item, container, false)

        val edit_foodName = view.findViewById<EditText>(R.id.add_foodName)
        val edit_foodPrice = view.findViewById<EditText>(R.id.add_foodPrice)
        val edit_foodTime = view.findViewById<EditText>(R.id.add_foodTime)
        val edit_foodURL = view.findViewById<EditText>(R.id.add_foodUrl)
        val editItem = view.findViewById<MaterialButton>(R.id.add_editItem)
        val highlight = view.findViewById<Switch>(R.id.switch_highlight)
        val categoriaSpinner = view.findViewById<Spinner>(R.id.textInputLayout9)
        val subCategoriaSpinner = view.findViewById<Spinner>(R.id.textInputLayout10)
        val deleteItem = view.findViewById<ImageButton>(R.id.deleteItem)
        val backBtnEditItem = view.findViewById<ImageButton>(R.id.backBtnEditItem)

        backBtnEditItem.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val prefs: SharedPreferences? = activity?.getSharedPreferences(
            "id_item",
            Context.MODE_PRIVATE
        )

        var id_item = 0
        id_item = prefs!!.getInt("id_item", 0)

        Backend.getItemID(id_item) {

            item = it

            deleteItem.setOnClickListener {
                Backend.deleteItem(item!!.id_item!!){
                    if (it) {
                        Toast.makeText(requireActivity(),"Eliminado!", Toast.LENGTH_SHORT).show()
                        fragmentManager?.popBackStack()
                    }

                    else
                        Toast.makeText(requireActivity(),"Nao Eliminado!", Toast.LENGTH_SHORT).show()
                }
            }

            edit_foodName.setText(it.nome)
            edit_foodPrice.setText(it.preco.toString())
            edit_foodTime.setText(it.temp_prep.toString())
            edit_foodURL.setText(it.url)

            Backend.getAllCategoryNames {

                var idCategoria: Int? = null
                var idsubCategoria: Int? = null

                categorias = it

                categoriaSpinner?.adapter = ArrayAdapter(
                    activity?.applicationContext!!,
                    R.layout.dropdownitem, categorias
                )

                categoriaSpinner.setSelection(item!!.id_categoria!! - 1)


                Backend.getAllSubcategoryNames {
                    subcategorias = it

                    subCategoriaSpinner?.adapter = ArrayAdapter(
                        activity?.applicationContext!!,
                        R.layout.dropdownitem, subcategorias
                    )

                    subCategoriaSpinner.setSelection(item!!.id_subcategoria!! - 1)

                    editItem.setOnClickListener {

                        var categorySelected = categoriaSpinner.selectedItem.toString()
                        var subcategorySelected = subCategoriaSpinner.selectedItem.toString()

                        if (edit_foodName.text.isNullOrBlank() || edit_foodPrice.text.isNullOrBlank() || edit_foodTime.text.isNullOrBlank() || edit_foodURL.text.isNullOrBlank()) {
                            Toast.makeText(
                                requireActivity(),
                                R.string.null_data,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Backend.getIDCategory(categorySelected) {
                                idCategoria = it

                                Backend.getIDSubcategory(subcategorySelected) {
                                    idsubCategoria = it

                                    var item = Item_Menu(
                                        id_item, edit_foodName.text.toString(),
                                        edit_foodPrice.text.toString().toDouble(),
                                        edit_foodTime.text.toString().toInt(),
                                        highlight(), edit_foodURL.text.toString(), idCategoria,
                                        idsubCategoria, 0.0
                                    )

                                    Backend.updateItem(id_item, item) {
                                        if (it) {
                                            Toast.makeText(
                                                requireActivity(),
                                                item.nome + " editado",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                requireActivity(),
                                                "ERRO",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return view
    }

    fun highlight() : Boolean {

        val destaque = requireView().findViewById<Switch>(R.id.switch_highlight)
        if (destaque.isChecked) {
            return true
        } else {
            return false
        }
    }
}