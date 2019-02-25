package com.avelozo.mychoice.request

import com.avelozo.mychoice.retrofit.RetrofitFactory
import io.reactivex.Single

class ImageRequest (private val retrofitFactory: RetrofitFactory) : IImageRequest {

    override fun getImages(search: String, imgQuantity: Int) : Single<ImageReceiver> {
        return try {
             retrofitFactory.getRetrofit()
                .create(ImageService::class.java)
                .getImages( search = search ,
                            pageSize = imgQuantity)
                 .doOnSuccess { it.category = search }

        }catch(ex: Exception){
             Single.error(ex)
        }
    }
}