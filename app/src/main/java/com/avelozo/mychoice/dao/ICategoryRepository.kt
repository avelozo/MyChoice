package com.avelozo.mychoice.dao

import com.avelozo.mychoice.model.Category

interface ICategoryRepository {

    fun insertCategory(category: Category)
    fun getCategories(): MutableList<Category>
    fun addItemClicked(category: Category)
}
