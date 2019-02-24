package com.avelozo.mychoice.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitFactory {

    private val   TIMEOUT = 35.toLong()
    private lateinit var mRetrofit : Retrofit
    private val  BASE_URL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/"


    init {
        if (!::mRetrofit.isInitialized) createRetrofit()
    }

    companion object {
        fun getInstance(): RetrofitFactory {
            return Single.Instance
        }
    }

    private object Single {
        val Instance = RetrofitFactory()
    }

    fun getRetrofit() : Retrofit{
        return mRetrofit
    }

    private fun createRetrofit() {
        mRetrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(constructClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    private fun constructClient() : OkHttpClient{
        val httpClient =  OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)


        httpClient.addInterceptor { chain ->
            val requestHeader = chain.request()
                .newBuilder()
                .addHeader("X-RapidAPI-Key", "580229cba9mshab49b23cfffc6c4p1c49f8jsn164d6fcca36f")

            val request = requestHeader.build()
            chain.proceed(request)
        }

            return httpClient.build()
    }
}