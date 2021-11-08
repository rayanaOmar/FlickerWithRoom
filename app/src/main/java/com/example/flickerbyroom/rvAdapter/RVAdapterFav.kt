package com.example.flickerbyroom.rvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickerbyroom.FavoritePage
import com.example.flickerbyroom.R
import com.example.flickerbyroom.roomDatabase.Flicker
import kotlinx.android.synthetic.main.item_row2.view.*

class RVAdapterFav(private val activity: FavoritePage, private val names: List<Flicker>) : RecyclerView.Adapter<RVAdapterFav.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row2, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = names[position]


        holder.itemView.apply {
            title.text = photo.title

            Glide.with(holder.itemView.context)
                .load(photo.link)
                .into(imageView2)
        }
    }

    override fun getItemCount() = names.size
}