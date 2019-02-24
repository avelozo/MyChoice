package com.avelozo.mychoice.request

import com.google.gson.annotations.SerializedName


class ImageReceiver {

    var category : String = ""
    @SerializedName("value")
    var values: List<CategoryResult>? = null

     inner class CategoryResult(
         var  url: String
    )
}