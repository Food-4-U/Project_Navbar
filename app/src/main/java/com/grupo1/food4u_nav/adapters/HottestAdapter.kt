package projeto.ipca.food4u.grupoI.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.internal.ContextUtils.getActivity
import com.grupo1.food4u_nav.ProductDetailsActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.models.Item_Menu
import com.grupo1.food4u_nav.models.data.CartItem
import com.grupo1.food4u_nav.models.data.CartViewModel
import com.squareup.picasso.Picasso


class HottestAdapter(val context: Context,val itens: List<Item_Menu>) : RecyclerView.Adapter<HottestAdapter.ViewHolder>() {

    private lateinit var mCartViewModel: CartViewModel

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoFood = itemView.findViewById<ShapeableImageView>(R.id.menu_foodPhoto)
        var nameFood = itemView.findViewById<TextView>(R.id.menu_nameFood)
        var foodprice = itemView.findViewById<TextView>(R.id.menu_foodPrice)
        var foodEvaluation = itemView.findViewById<TextView>(R.id.menu_foodEvauation)
        var foodStars = itemView.findViewById<RatingBar>(R.id.ratingBar_hottest)
        var addBtn = itemView.findViewById<ImageView>(R.id.addBtnHottest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_hottest, parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: HottestAdapter.ViewHolder, position: Int) {
        var price = String.format("%.2f", itens[position].preco)
        holder.foodprice.text = price.plus("€")
        holder.nameFood.text = itens[position].nome
        holder.foodEvaluation.text = itens[position].avaliação.toString()
        holder.foodStars.rating = itens[position].avaliação!!.toFloat()


        var imageURL = itens[position].url
        Picasso.get().load(imageURL).resize(800,650).into(holder.photoFood)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id_item", itens[position].id_item)
            context.startActivity(intent)
        }

        holder.addBtn.setOnClickListener{
            Toast.makeText(
                context,
                "Adicionado ao Pedido.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}