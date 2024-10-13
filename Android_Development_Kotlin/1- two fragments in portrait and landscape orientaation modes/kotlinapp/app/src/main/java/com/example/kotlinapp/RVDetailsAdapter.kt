package com.example.kotlinapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ViewHolder2(private val item: View): RecyclerView.ViewHolder(item)
{
    val imageView : ImageView = item.findViewById(R.id.imageView)
    val editTitle : TextView = item.findViewById(R.id.editTitle)
    val editPrice : TextView = item.findViewById(R.id.editPrice)
    val editBrand : TextView = item.findViewById(R.id.editBrand)
    val editDescription : TextView = item.findViewById(R.id.editDescription)
    val ratingBarr : RatingBar = item.findViewById(R.id.ratingBarr)
    val btndeleteToFav : Button = item.findViewById(R.id.btndeleteToFav)
}


class RVDetailsAdapter : ListAdapter<Product, ViewHolder2>(ProductDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val myinflater : LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = myinflater.inflate(R.layout.recycle_layout,parent,false)
        return  ViewHolder2((view))
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {

        Log.i("TAG", getItem(position).title)
        Log.i("TAG", getItem(position).brand)
        Log.i("TAG", getItem(position).price.toString())
        Log.i("TAG", getItem(position).description)
        holder.editTitle.text = getItem(position).title
        holder.editBrand.text = getItem(position).brand
        holder.editPrice.text = getItem(position).price.toString()
        holder.editDescription.text = getItem(position).description

        Glide.with(holder.itemView.context)
            .load(getItem(position).thumbnail) // Replace with your image URL getter
            .apply(
                RequestOptions()
                    .override(200, 200) // Set size for the image
                    .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
                    .error(R.drawable.ic_launcher_foreground) // Error image
            )
            .into(holder.imageView)
//            .into(holder.imageView)
    }
}