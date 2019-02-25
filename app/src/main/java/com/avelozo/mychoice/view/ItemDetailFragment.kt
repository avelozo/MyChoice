package com.avelozo.mychoice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.avelozo.mychoice.R
import com.avelozo.mychoice.view.FragmentUtils.IMGURL
import com.bumptech.glide.Glide

class ItemDetailFragment : FragmentAbstract() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = arguments?.getString(IMGURL)
        val imgView = view.findViewById<ImageView>(R.id.imgDogDetail)
        Glide
            .with(view)
            .load(imageUrl)
            .placeholder(R.drawable.dog_shadow)
            .into(imgView)
    }
}




