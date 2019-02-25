package com.avelozo.mychoice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avelozo.mychoice.R
import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.model.Item
import com.bumptech.glide.Glide

class ItemAdapter(private val items: List<Item>, private val onClick: (Item) -> Unit) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemSelectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_selection_item, parent, false))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemSelectionViewHolder

        Glide
            .with(holder.itemView)
            .load(items[position].imageUrl)
            .placeholder(R.drawable.dog_shadow)
            .into(holder.itemImage)


        holder.itemImage.setOnClickListener {
            onClick(items[position])
        }
    }

    inner class ItemSelectionViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val itemImage : ImageView = mView.findViewById(R.id.img_item)
    }
}