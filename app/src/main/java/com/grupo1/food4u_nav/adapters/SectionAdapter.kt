package com.grupo1.food4u_nav.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.SectionMenu

class SectionAdapter(private  val context: Context, private val allCategory: List<SectionMenu>) :
    RecyclerView.Adapter<SectionAdapter.MainViewHolder>() {


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        public var categoryTitle : TextView
        var itemRecycler : RecyclerView

        init{
                categoryTitle = itemView.findViewById(R.id.categoryTitle)
                itemRecycler = itemView.findViewById(R.id.categoryItemRv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.categoryTitle.text = allCategory[position].categoria
        setItemsRV(holder.itemRecycler, allCategory[position].itens)

    }

    override fun getItemCount(): Int {
        return allCategory.size
    }

    private fun setItemsRV(recyclerView: RecyclerView, categoryItem: List<Item_Menu>){

        val itemRecyclerAdapter = ItemAdapter(context, categoryItem)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = itemRecyclerAdapter

    }
}