package com.avelozo.mychoice.presenter

import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.interactor.IItemInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ItemSelectionPresenter(var itemInteractor: IItemInteractor) : ItemSelectionFragmentContract.Presenter() {

    override fun getSelectedItems(search: String){
        itemInteractor
            .getSelectedItems(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ items ->
                view?.loadItemsRecycler(items)
            },{
                view?.showLoadItemError()
            }).apply {
                disposables.add(this)
            }
    }
}