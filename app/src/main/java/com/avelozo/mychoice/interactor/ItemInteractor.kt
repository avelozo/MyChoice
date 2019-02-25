package com.avelozo.mychoice.interactor

import com.avelozo.mychoice.model.Item
import com.avelozo.mychoice.request.IImageRequest
import io.reactivex.Single

class  ItemInteractor(private var imageRequest: IImageRequest) : IItemInteractor {

    private val IMG_QUANTITY = 12

    override fun getSelectedItems(search: String): Single<List<Item>> {
      return   imageRequest
            .getImages(search,imgQuantity = IMG_QUANTITY)
            .map { images -> images.values?.map { Item(images.category, it.url) } }
    }

}