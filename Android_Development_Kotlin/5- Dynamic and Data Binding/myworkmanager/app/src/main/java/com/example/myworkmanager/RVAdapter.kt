package com.example.kotlinapp

import com.example.myworkmanager.Product

import com.example.myworkmanager.ProductResponse
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myworkmanager.MainActivity2
import com.example.myworkmanager.R
import com.example.myworkmanager.databinding.RecycleLayoutBinding
import com.squareup.picasso.Picasso

@BindingAdapter("myImageUrl","myError")
fun loadImage(view:ImageView, url: String, myError: Drawable) {
    Picasso.get().load(url).into(view)
}
//step 1 for adaptor dynamic Binding
class ViewHolder(var binding: RecycleLayoutBinding  ): RecyclerView.ViewHolder(binding.root)
{
    //val imageView : ImageView = item.findViewById(R.id.imageView)
    //val editTitle : TextView = item.findViewById(R.id.editTitle)
    //val editPrice : TextView = item.findViewById(R.id.editPrice)
    //val editBrand : TextView = item.findViewById(R.id.editBrand)
    //val editDescription : TextView = item.findViewById(R.id.editDescription)
    //val ratingBarr : RatingBar = item.findViewById(R.id.ratingBarr)
    //val btndeleteToFav : Button = item.findViewById(R.id.btndeleteToFav)


}

public class RVAdapter(private val fragment: Fragment , private val myOnItemClickListener:OnItemClickListener) : ListAdapter<Product, ViewHolder> (ProductDiffUtil())
{
    var fmgr: FragmentManager? = null
    lateinit var binding: RecycleLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val myinflater : LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //step 2 of adaptor dynamic binding
        binding = RecycleLayoutBinding.inflate(myinflater,parent,false )

        //val view = myinflater.inflate(R.layout.recycle_layout,parent,false)
        return  ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.imageView.text =
        holder.binding.ratingBarr.visibility = View.GONE
        holder.binding.btndeleteToFav.visibility = View.GONE

        holder.binding.productitem =getItem(position)

        //holder.binding.editTitle.text = getItem(position).title.toString()
        ////holder.editBrand.text = getItem(position).brand
        //holder.binding.editPrice.text = getItem(position).price.toString()
        //holder.binding.editDescription.text = getItem(position).description
        //Glide.with(holder.itemView.context)
        //    .load(getItem(position).thumbnail) // Replace with your image URL getter
        //    .apply(
        //        RequestOptions()
        //            .override(200, 200) // Set size for the image
        //            .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
        //            .error(R.drawable.ic_launcher_foreground) // Error image
        //    )
        //    .into(holder.binding.imageView)

        holder.itemView.setOnClickListener(
            {



                // Check if the orientation is landscape
                if (fragment.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    //fragment.parentFragmentManager.beginTransaction()
                    //    .replace(R.id.fragmentB2, FragmentB2())
                    //    .commit()

                    //myOnItemClickListener.onItemClicked("dc")
                    val productList = listOf(getItem(position))
                    myOnItemClickListener.onItemClicked(productList)


                }
                else
                {
                    // Create an Intent to start ProductDetailActivity
                    val intent = Intent(holder.itemView.context, MainActivity2::class.java)

                    // Pass the Product object to the new Activity
                    intent.putExtra("PRODUCT_DATA", getItem(position)) // For Serializable

                    // Start the newva
                    holder.itemView.context.startActivity(intent)
                }

            }
        )
    }


}