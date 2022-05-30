package projeto.ipca.food4u.grupoI.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.internal.ContextUtils.getActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.squareup.picasso.Picasso


class HottestAdapter(val context: Context,val itens: List<Item_Menu>) : RecyclerView.Adapter<HottestAdapter.ViewHolder>() {

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

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: HottestAdapter.ViewHolder, position: Int) {
        holder.nameFood.text = itens[position].nome
        holder.foodEvaluation.text = itens[position].avaliação.toString()
        holder.foodprice.text = itens[position].preco.toString() + "0€"
        holder.foodStars.rating = itens[position].avaliação!!.toFloat()

        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", itens[position].id_item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}