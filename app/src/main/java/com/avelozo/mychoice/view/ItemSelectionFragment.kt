package com.avelozo.mychoice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import com.avelozo.mychoice.R
import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.model.Item
import com.avelozo.mychoice.view.FragmentUtils.IMGURL
import com.avelozo.mychoice.view.FragmentUtils.SEARCH
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.fragment_first.*

class ItemSelectionFragment : FragmentAbstract(), ItemSelectionFragmentContract.View {

    private val presenter: ItemSelectionFragmentContract.Presenter by injector.instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lbl_interested_in.visibility = View.GONE
        presenter.attach(this)

        val search = arguments?.getString(SEARCH)
        search?.let {
            presenter.getSelectedItems(it)
        }
        }


    override fun loadItemsRecycler(items: List<Item>) {
        recyclerviewImages.adapter = ItemAdapter(items){
            val fragment = ItemDetailFragment()
            val bundle = Bundle()
            bundle.putString(SEARCH, it.category)
            bundle.putString(IMGURL, it.imageUrl)
            fragment.arguments = bundle
            fragmentManager
                ?.beginTransaction()
                ?.replace(com.avelozo.mychoice.R.id.fragmentContainer, fragment)
                ?.commit()
        }

        val layoutManager = GridLayoutManager(context, 3)

        recyclerviewImages.layoutManager = layoutManager
        recyclerviewImages.adapter?.notifyDataSetChanged()
    }


    override fun showLoadItemError(){
     Toast.makeText(context, "Could not load items", Toast.LENGTH_LONG).show()
    }
}




