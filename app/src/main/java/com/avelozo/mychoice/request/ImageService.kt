package com.avelozo.mychoice.request


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("Search/ImageSearchAPI")
    fun getImages(
        @Query("q") search: String,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("safeSearch") safeSearch: Boolean = true
                 ): Single<ImageReceiver>
}