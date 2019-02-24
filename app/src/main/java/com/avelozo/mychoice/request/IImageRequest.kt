package com.avelozo.mychoice.request


import io.reactivex.Single

interface IImageRequest {
    fun getImages(category: String, imgQuantity: Int) : Single<ImageReceiver>
}
