package com.avelozo.mychoice.contract

import com.avelozo.mychoice.model.Item

interface ItemSelectionFragmentContract {
    interface View : BaseView {
        fun showLoadItemError()
        fun loadItemsRecycler(items: List<Item>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getSelectedItems(search: String)
    }
}