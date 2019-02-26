package com.avelozo.mychoice.presenter

import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.model.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class FirstPresenter(private var categoryInteractor: ICategoryInteractor) : FirstFragmentContract.Presenter() {

    val SMALL_COLUMN_WEIGHT = 1
    val MEDIUM_COLUMN_WEIGHT= 2
    val BIG_COLUMN_WEIGHT   = 3

    override fun onViewCreated(){
        categoryInteractor
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({categories ->
                convertToWeightedList(categories as ArrayList<Category>)
                view?.loadCategoriesRecycler(categories)
            },{
                view?.showLoadCategoryError()
            }).apply {
                disposables.add(this)
            }
    }

    override fun addItemClicked(category: Category){
        categoryInteractor.addItemClicked(category)
    }

    private fun convertToWeightedList(categories : ArrayList<Category>){
        categories.sortByDescending { it.timesVisited }

        val totalTimesClicked = categories.sumBy { it.timesVisited }
        var totalAverage = 0.0
        if(categories.isNotEmpty())
            totalAverage = (totalTimesClicked / categories.size).toDouble()

        if(categories.first().timesVisited > totalAverage && categories[1].timesVisited >= 1) {
            categories.first().weight = BIG_COLUMN_WEIGHT
            categories[1].weight = MEDIUM_COLUMN_WEIGHT
        }

    }

}