package com.grupo1.food4u_nav.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.SubCategories
import com.grupo1.food4u_nav.ui.admin.EditCategorySub
import com.squareup.picasso.Picasso

class ListViewAdapterSub (var context: Context, var expandableListView : ExpandableListView, var header : MutableList<String>, var body : MutableList<MutableList<SubCategories>>): BaseExpandableListAdapter() {
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
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): SubCategories {
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
        title.text = getChild(groupPosition,childPosition).name

        val editImage = convertView!!.findViewById<ImageView>(R.id.editImage)
        Picasso.get().load(pos.url).fit().centerCrop().into(editImage)

        convertView.setOnClickListener {
            val fragment = EditCategorySub()
            val args = Bundle()
            var data = arrayOf("1",pos.id_SubCategory.toString(),pos.name,pos.url)
            args.putStringArray("data",data)
            fragment.arguments = args

            val mFragmentManager = (context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = mFragmentManager.beginTransaction()
            transaction.replace(R.id.containerMenuManage, fragment)
            transaction.addToBackStack("null")
            transaction.commit()
        }



        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }
}