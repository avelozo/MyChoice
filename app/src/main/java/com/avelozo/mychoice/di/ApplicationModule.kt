package com.avelozo.mychoice.di

import com.avelozo.mychoice.app.MainApplication
import com.avelozo.mychoice.contract.FirstFragmentContract
import com.avelozo.mychoice.contract.ItemSelectionFragmentContract
import com.avelozo.mychoice.dao.CategoryRepository
import com.avelozo.mychoice.dao.DatabaseHelper
import com.avelozo.mychoice.dao.ICategoryRepository
import com.avelozo.mychoice.interactor.CategoryInteractor
import com.avelozo.mychoice.interactor.ICategoryInteractor
import com.avelozo.mychoice.interactor.IItemInteractor
import com.avelozo.mychoice.interactor.ItemInteractor
import com.avelozo.mychoice.presenter.FirstPresenter
import com.avelozo.mychoice.presenter.ItemSelectionPresenter
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

        bind<FirstFragmentContract.Presenter>() with provider{ FirstPresenter(instance()) }

        bind<ItemSelectionFragmentContract.Presenter>() with provider { ItemSelectionPresenter(instance()) }

        bind<IItemInteractor>() with provider { ItemInteractor(instance()) }


    }

}