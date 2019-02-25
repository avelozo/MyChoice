package com.avelozo.mychoice.interactor


import com.avelozo.mychoice.model.Item
import com.avelozo.mychoice.request.ImageReceiver
import io.reactivex.Single

interface IItemInteractor {

    fun getSelectedItems(search: String): Single<List<Item>>
}
