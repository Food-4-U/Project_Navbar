package com.grupo1.food4u_nav.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.ui.admin.EditItemFragment
import com.squareup.picasso.Picasso

class HighlightEditAdapter (val context: Context, val itens: List<Item_Menu>) : RecyclerView.Adapter<HighlightEditAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ImageView>(R.id.productPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.productTitle)
        var foodprice = itemView.findViewById<TextView>(R.id.priceProduct)
        var removeHightlight = itemView.findViewById<Button>(R.id.deleteHightlight)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_edithighlight, parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: HighlightEditAdapter.ViewHolder, position: Int) {
        var price = String.format("%.2f", itens[position].preco)
        holder.foodprice.text = price.plus("€")
        holder.nameFood.text = itens[position].nome

        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.removeHightlight.setOnClickListener {
          itens[position].destaque = false
             Backend.updateItem(itens[position].id_item!!, itens[position]) {
                 if (it)
                    Toast.makeText(context,"Retirado dos favoritos",Toast.LENGTH_SHORT)
                 else
                     Toast.makeText(context,"Nao removeu dos favoritos",Toast.LENGTH_SHORT)

             }
        }
    }


    override fun getItemCount(): Int {
        return itens.size
    }

}