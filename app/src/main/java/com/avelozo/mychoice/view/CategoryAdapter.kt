package com.avelozo.mychoice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avelozo.mychoice.R
import com.avelozo.mychoice.model.Category
import com.bumptech.glide.Glide

class CategoryAdapter(private val categories: ArrayList<Category>, private val onClick: (Category) -> Unit) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun getItemCount(): Int {
       return categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CategoryViewHolder

        Glide
            .with(holder.itemView)
            .load(categories[position].imageUrl)
            .placeholder(R.drawable.dog_shadow)
            .into(holder.categoryImage)


        holder.categoryName.text = categories[position].name
        holder.categoryImage.setOnClickListener {
            onClick(categories[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return categories[position].timesVisited +1
    }
    inner class CategoryViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val categoryImage : ImageView = mView.findViewById(R.id.img_category)
        val categoryName : TextView = mView.findViewById(R.id.txtCategory)
    }
}