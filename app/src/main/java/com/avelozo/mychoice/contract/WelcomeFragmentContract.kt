package com.avelozo.mychoice.contract

import com.avelozo.mychoice.model.Category

interface WelcomeFragmentContract {
    interface View : BaseView {
        fun showLoadCategoryError()
        fun loadMostViewedCategory(category: Category)
        fun setEvents()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun onViewCreated()
    }
}