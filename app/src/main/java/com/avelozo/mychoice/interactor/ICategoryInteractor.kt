package com.avelozo.mychoice.interactor

import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.request.ImageReceiver
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface ICategoryInteractor {

    fun getAllCategories(): Single<MutableList<Category>>
    fun getCategoryImage(search: String) : Observable<ImageReceiver>
    fun saveCategory(category: Category)
    fun initCategories(): Single<MutableList<Category>>
}
