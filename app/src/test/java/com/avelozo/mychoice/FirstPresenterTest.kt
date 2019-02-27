package com.avelozo.mychoice

import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.presenter.FirstPresenter
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import java.lang.Exception
import org.junit.*

class FirstPresenterTest {
    companion object {
        @ClassRule
        @JvmField
         val schedulers = RxImmediateSchedulerRule()
    }

    lateinit var presenter: FirstFragmentContract.Presenter

    private val categoryInteractor: ICategoryInteractor = mock()
    private val view: FirstFragmentContract.View = mock()



    @Before
    fun setUp() {
        presenter = FirstPresenter(categoryInteractor)
        presenter.attach(view)
    }

    @Test
    fun loadCategories() {
        doReturn(Single.just(getCategories())).whenever(categoryInteractor).getAllCategories()

        presenter.onViewCreated()
        verify(view, times(1)).loadCategoriesRecycler(any())
    }


    @Test
    fun showCategoryErrorMessage() {
        doReturn(Single.error<Exception>(Exception())).whenever(categoryInteractor).getAllCategories()

        presenter.onViewCreated()

        verify(view, times(1)).showLoadCategoryError()

    }


    @Test
    fun addItemClicked() {
        val category = Category()
        presenter.addItemClicked(category)
        verify(categoryInteractor, times(1)).addItemClicked(category)

    }

    fun getCategories() = mutableListOf(Category("Husky", "http.image.png", 1, 0))

}


