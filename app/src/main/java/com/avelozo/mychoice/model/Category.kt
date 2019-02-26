package com.avelozo.mychoice.model

data class Category (
    val name : String = "",
    val imageUrl : String = "",
    var timesVisited: Int = 0,
    var weight: Int = 1
    )