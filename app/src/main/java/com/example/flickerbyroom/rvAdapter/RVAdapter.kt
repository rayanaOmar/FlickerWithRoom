package com.example.flickerbyroom.rvAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickerbyroom.ImageViewPage
import com.example.flickerbyroom.R
import com.example.flickerbyroom.details.Photo
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(private val names: ArrayList<Photo>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = names[position]

        holder.itemView.apply {
            title.text = photo.title
            val link="https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            Glide.with(holder.itemView.context)
                .load(link)
                .into(imageView2)
            //when chick on image go to ImageViewPage with 2 values (title and link)
            clItr.setOnClickListener {
                val info = Intent(holder.itemView.context, ImageViewPage::class.java)
                info.putExtra("title", photo.title)
                info.putExtra("link", link)
                holder.itemView.context.startActivity(info)

            }
        }
    }
    override fun getItemCount() = names.size
}