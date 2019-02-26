package com.avelozo.mychoice.presenter

import com.avelozo.mychoice.contract.WelcomeFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
class WelcomePresenter(var categoryInteractor: ICategoryInteractor) : WelcomeFragmentContract.Presenter() {

    override fun onViewCreated(){
        view?.setEvents()
        categoryInteractor
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({categories ->
                categories.sortByDescending { it.timesVisited }
                view?.loadMostViewedCategory(categories.first())
            },{
                view?.showLoadCategoryError()
            }).apply {
                disposables.add(this)
            }
    }
}