package com.avelozo.mychoice.contract

import com.avelozo.mychoice.model.Category
import kotlin.collections.ArrayList

interface CategoryListFragmentContract {
    interface View : BaseView {

        fun loadCategoriesRecycler( categories : ArrayList<Category>)
        fun showLoadCategoryError()

    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun onViewCreated()
        abstract fun addItemClicked(category: Category)
    }
}