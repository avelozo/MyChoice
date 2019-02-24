package com.avelozo.mychoice.request

import com.avelozo.mychoice.retrofit.RetrofitFactory
import io.reactivex.Maybe
import io.reactivex.Single
import java.lang.Exception

class ImageRequest (private val retrofitFactory: RetrofitFactory) : IImageRequest {

    override fun getImages(category: String, imgQuantity: Int) : Single<ImageReceiver> {
        return try {
             retrofitFactory.getRetrofit()
                .create(ImageService::class.java)
                .getImages( search = category ,
                            pageSize = imgQuantity)
                 .doOnSuccess { it.category = category }


        }catch(ex: Exception){
             Single.error(ex)
        }
    }
}