package com.avelozo.mychoice.interactor

import com.avelozo.mychoice.dao.ICategoryRepository
import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.request.IImageRequest
import com.avelozo.mychoice.request.ImageReceiver
import io.reactivex.Observable
import io.reactivex.Single

class  CategoryInteractor(private var imageRequest: IImageRequest,
                          private var categoryRepository:  ICategoryRepository) : ICategoryInteractor {

    private val IMG_QUANTITY = 10

    override fun saveCategory(category: Category){
        categoryRepository.insertCategory(category)
    }

    override fun addItemClicked(category: Category){
        categoryRepository.addItemClicked(category)
    }

    override fun getCategoryImage(search: String) : Observable<ImageReceiver>{
        return imageRequest
            .getImages(search, IMG_QUANTITY).toObservable()
    }

    override fun getAllCategories(): Single<MutableList<Category>> {
        val categories = categoryRepository.getCategories()
        return if(categories.isNullOrEmpty()){
           initCategories()
        } else {
             Single.create { emitter ->
                try {
                    emitter.onSuccess(categories)
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }
    }

    override fun initCategories() : Single<MutableList<Category>> {
       return  Observable
            .fromIterable(generateCategoriesNames())
            .concatMap { getCategoryImage(it)
                .map{
                    val category = Category(it.category, it.values?.first()?.url ?: "", 0)
                    saveCategory(category)
                    category
                }
            }
           .toList()

    }


    private fun generateCategoriesNames() : ArrayList<String>{
        return arrayListOf("Chihuahua", "Malamute", "Puppies", "Poodle", "Saluki", "Greyhound")
    }
}