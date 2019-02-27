package com.avelozo.mychoice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.avelozo.mychoice.R
import com.avelozo.mychoice.contract.WelcomeFragmentContract
import com.avelozo.mychoice.model.Category
import com.bumptech.glide.Glide
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : FragmentAbstract(), WelcomeFragmentContract.View{

    private val presenter: WelcomeFragmentContract.Presenter by injector.instance()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_welcome, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.onViewCreated()
    }


    override fun setEvents(){
        btnShowCategories.setOnClickListener {
            loadFragment(CategoryListFragment())
        }

    }

    override fun loadMostViewedCategory(category: Category){
        view?.let {
            val imgView = it.findViewById<ImageView>(R.id.imgMostViewed)
            Glide
                .with(it)
                .load(category.imageUrl)
                .placeholder(R.drawable.dog_shadow)
                .into(imgView)

        }
    }

    override fun showLoadCategoryError() {
        Toast.makeText(context, getString(R.string.error_loading_category), Toast.LENGTH_LONG ).show()
    }
}