package com.avelozo.mychoice.di

import com.avelozo.mychoice.app.MainApplication
import com.avelozo.mychoice.contract.CategoryListFragmentContract
import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.contract.WelcomeFragmentContract
import com.avelozo.mychoice.dao.CategoryRepository
import com.avelozo.mychoice.dao.DatabaseHelper
import com.avelozo.mychoice.dao.ICategoryRepository
import com.avelozo.mychoice.interactor.CategoryInteractor
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.interactor.IItemInteractor
import com.avelozo.mychoice.interactor.ItemInteractor
import com.avelozo.mychoice.presenter.CategoryListPresenter
import com.avelozo.mychoice.presenter.ItemSelectionPresenter
import com.avelozo.mychoice.presenter.WelcomePresenter
import com.avelozo.mychoice.request.IImageRequest
import com.avelozo.mychoice.request.ImageRequest
import com.avelozo.mychoice.retrofit.RetrofitFactory
import com.github.salomonbrys.kodein.*

class ApplicationModule {

    val module = Kodein.Module {

        bind<RetrofitFactory>() with singleton { RetrofitFactory() }

        bind<DatabaseHelper>() with singleton { DatabaseHelper(MainApplication.mInstance) }

        bind<IImageRequest>() with provider { ImageRequest(instance())}

        bind<ICategoryInteractor>() with provider { CategoryInteractor(instance(), instance()) }

        bind<ICategoryRepository>() with provider { CategoryRepository(instance()) }

        bind<CategoryListFragmentContract.Presenter>() with provider{ CategoryListPresenter(instance()) }

        bind<ItemSelectionFragmentContract.Presenter>() with provider { ItemSelectionPresenter(instance()) }

        bind<IItemInteractor>() with provider { ItemInteractor(instance()) }

        bind<WelcomeFragmentContract.Presenter>() with provider { WelcomePresenter(instance()) }

    }

}