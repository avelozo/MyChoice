package com.avelozo.mychoice.dao

import android.provider.BaseColumns


object DatabaseContract {

    object CategoryEntry : BaseColumns {
        const val CATEGORY_TABLE_NAME = "category"
        const val COLUMN_NAME_CATEGORY_NAME= "category_name"
        const val COLUMN_NAME_CATEGORY_URL= "category_url"
        const val COLUMN_NAME_CATEGORY_VISITS = "category_visits"
    }
}