package projeto.ipca.food4u.grupoI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu


class HottestAdapter(val itens: List<Item_Menu>) : RecyclerView.Adapter<HottestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.menu_foodPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.menu_nameFood)
        var foodprice = itemView.findViewById<TextView>(R.id.menu_foodPrice)
        var foodEvaluation = itemView.findViewById<TextView>(R.id.menu_foodEvauation)
        var foodStars = itemView.findViewById<RatingBar>(R.id.ratingBar_hottest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_hottest, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HottestAdapter.ViewHolder, position: Int) {
        holder.nameFood.text = itens[position].nome
        holder.foodEvaluation.text = itens[position].avaliacao.toString()
        holder.foodprice.text = itens[position].preco.toString() + "€"
        holder.foodStars.rating = itens[position].avaliacao
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}