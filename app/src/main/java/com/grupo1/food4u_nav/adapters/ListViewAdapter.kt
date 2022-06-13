package com.grupo1.food4u_nav.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.CategoryType
import com.squareup.picasso.Picasso

class ListViewAdapter (var context: Context, var expandableListView : ExpandableListView,
                       var header : MutableList<String>, var body : MutableList<MutableList<CategoryType>>)
    : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.header_expandable,null)
        }

        val title = convertView?.findViewById<TextView>(R.id.header)

        title?.text = getGroup(groupPosition)

        convertView?.setOnClickListener {
            if(expandableListView.isGroupExpanded(groupPosition))
                expandableListView.collapseGroup(groupPosition)
            else
                expandableListView.expandGroup(groupPosition)
            Toast.makeText(context, getGroup(groupPosition), Toast.LENGTH_SHORT).show()
        }


        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): CategoryType {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.row_expandable,null)
        }

        val pos = getChild(groupPosition,childPosition)

        val title = convertView!!.findViewById<TextView>(R.id.nameItem)
        title.text = pos.name

        val editImage = convertView!!.findViewById<ImageView>(R.id.editImage)
        Picasso.get().load(pos.url).fit().centerCrop().into(editImage)

        convertView.setOnClickListener {
            Toast.makeText(context, pos.id.toString(), Toast.LENGTH_SHORT).show()
            showDialog(pos)
        }


        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }


    @SuppressLint("ResourceType")
    private fun showDialog(category: CategoryType) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_editdialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        var subcategoryname = dialog.findViewById(R.id.titleCategorySubDialog) as TextView
        subcategoryname.text =  context.getString(R.string.editCategory)

        var categorySubEditText = dialog.findViewById(R.id.categorySubEditText) as EditText
        categorySubEditText.setText(category.name)

        var submitCategorySub = dialog.findViewById(R.id.submitCategorySub) as TextView

        category.name = categorySubEditText.text.toString()

        submitCategorySub.setOnClickListener {
            Backend.updateCategory(category.id!!, category){
                if (!it)
                    Toast.makeText(context, "Erro ao atualizar!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Atualizou", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        val deleteCategorySub = dialog.findViewById<ImageButton>(R.id.deleteCategorySub)
        deleteCategorySub.setOnClickListener {
            Backend.deleteCategory(category.id!!.toInt()){
                if (!it)
                    Toast.makeText(context,"Erro ao remover!", Toast.LENGTH_SHORT).show()
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