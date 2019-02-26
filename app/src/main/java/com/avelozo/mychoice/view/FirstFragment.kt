package com.avelozo.mychoice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.avelozo.mychoice.R

import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.view.FragmentUtils.SEARCH
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.fragment_first.*



class FirstFragment : FragmentAbstract(), FirstFragmentContract.View {

    private val presenter: FirstFragmentContract.Presenter by injector.instance()
    private val GRID_QUANTITY = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.onViewCreated()

        }


    override fun loadCategoriesRecycler( categories : ArrayList<Category>){
        recyclerviewImages.adapter = CategoryAdapter(categories){
            presenter.addItemClicked(it)
            val fragment = ItemSelectionFragment()
            val bundle = Bundle()
            bundle.putString(SEARCH, it.name)
            fragment.arguments = bundle
            loadFragment(fragment)
        }

        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = recyclerviewImages.adapter?.getItemViewType(position)
                return viewType ?: 0
            }
        }

        val layoutManager = GridLayoutManager(context, GRID_QUANTITY)
        layoutManager.spanSizeLookup = spanSizeLookup

        recyclerviewImages.layoutManager = layoutManager
        recyclerviewImages.adapter?.notifyDataSetChanged()
    }

    override fun showLoadCategoryError(){
     Toast.makeText(context, getString(R.string.error_loading_category), Toast.LENGTH_LONG).show()
    }
}

object FragmentUtils{
    val SEARCH = "search"
    val IMGURL = "imgurl"
}



