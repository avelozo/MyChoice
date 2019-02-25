package com.avelozo.mychoice.presenter

import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.interactor.IItemInteractor
import com.avelozo.mychoice.interactor.ItemInteractor
import com.avelozo.mychoice.model.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

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