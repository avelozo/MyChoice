package com.avelozo.mychoice.presenter

import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.model.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class FirstPresenter(var categoryInteractor: ICategoryInteractor) : FirstFragmentContract.Presenter() {

    override fun onViewCreated(){
        categoryInteractor
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({categories ->
                view?.loadCategoriesRecycler(categories as ArrayList<Category>)
            },{
                view?.showLoadCategoryError()
            }).apply {
                disposables.add(this)
            }
    }
}