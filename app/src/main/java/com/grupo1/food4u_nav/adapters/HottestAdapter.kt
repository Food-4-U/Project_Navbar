package projeto.ipca.food4u.grupoI.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import projeto.ipca.food4u.grupoI.R
import projeto.ipca.food4u.grupoI.models.Item_Menu

class HottestAdapter(val itens: List<Item_Menu>) : RecyclerView.Adapter<HottestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.menu_foodPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.menu_nameFood)
        var foodprice = itemView.findViewById<TextView>(R.id.menu_foodPrice)
        var foodEvaluation = itemView.findViewById<TextView>(R.id.menu_foodEvauation)
        var foodTime = itemView.findViewById<TextView>(R.id.menu_foodTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_hottest, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HottestAdapter.ViewHolder, position: Int) {
        holder.nameFood.text = itens[position].description
        holder.foodTime.text = itens[position].time_prepare.toString() + " min"
        holder.foodEvaluation.text = itens[position].evaluation.toString()
        holder.foodprice.text = itens[position].price.toString() + "€"


    }

    override fun getItemCount(): Int {
        return itens.size
    }
}