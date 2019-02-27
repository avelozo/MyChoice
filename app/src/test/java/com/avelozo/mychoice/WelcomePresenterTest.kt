package com.avelozo.mychoice

import com.avelozo.mychoice.contract.WelcomeFragmentContract
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.model.Category
import com.avelozo.mychoice.presenter.WelcomePresenter
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import java.lang.Exception
import org.junit.*

class WelcomePresenterTest {
    companion object {
        @ClassRule
        @JvmField
         val schedulers = RxImmediateSchedulerRule()
    }

    lateinit var presenter: WelcomeFragmentContract.Presenter

    private val categoryInteractor: ICategoryInteractor = mock()
    private val view: WelcomeFragmentContract.View = mock()



    @Before
    fun setUp() {
        presenter = WelcomePresenter(categoryInteractor)
        presenter.attach(view)
    }

    @Test
    fun loadCategories() {
        val category = Category()
        doReturn(Single.just(getCategories())).whenever(categoryInteractor).getAllCategories()

        presenter.onViewCreated()
        verify(view, times(1)).setEvents()
        verify(view, times(1)).loadMostViewedCategory(getCategories().first())
    }


    @Test
    fun showCategoryErrorMessage() {
        doReturn(Single.error<Exception>(Exception())).whenever(categoryInteractor).getAllCategories()
        presenter.onViewCreated()

        verify(view, times(1)).showLoadCategoryError()

    }

    fun getCategories() = mutableListOf(Category("Husky", "http://www.image.png", 1, 0))

}


