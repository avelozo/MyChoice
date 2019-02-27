package com.avelozo.mychoice

import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.interactor.IItemInteractor
import com.avelozo.mychoice.model.Item
import com.avelozo.mychoice.presenter.ItemSelectionPresenter
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import java.lang.Exception
import org.junit.*

class ItemSelectionPresenterTest {
    companion object {
        @ClassRule
        @JvmField
         val schedulers = RxImmediateSchedulerRule()
    }

    lateinit var presenter: ItemSelectionFragmentContract.Presenter

    private val itemInteractor: IItemInteractor = mock()
    private val view: ItemSelectionFragmentContract.View = mock()



    @Before
    fun setUp() {
        presenter = ItemSelectionPresenter(itemInteractor)
        presenter.attach(view)
    }

    @Test
    fun loadCategories() {
        doReturn(Single.just(getItems())).whenever(itemInteractor).getSelectedItems("Husky")

        presenter.getSelectedItems("Husky")
        verify(view, times(1)).loadItemsRecycler(getItems())
    }


    @Test
    fun showCategoryErrorMessage() {
        doReturn(Single.error<Exception>(Exception())).whenever(itemInteractor).getSelectedItems("Husky")
        presenter.getSelectedItems("Husky")

        verify(view, times(1)).showLoadItemError()

    }

    fun getItems() = mutableListOf(Item("Husky", "http.image.png"))

}


