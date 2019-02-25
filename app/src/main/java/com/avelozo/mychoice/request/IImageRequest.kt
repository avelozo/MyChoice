package com.avelozo.mychoice.request


import io.reactivex.Single

interface IImageRequest {
    fun getImages(search: String, imgQuantity: Int) : Single<ImageReceiver>
}
