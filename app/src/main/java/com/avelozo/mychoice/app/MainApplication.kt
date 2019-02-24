package com.avelozo.mychoice.app

import android.app.Application
import android.content.Context
import com.avelozo.mychoice.di.ApplicationModule
import com.facebook.stetho.Stetho
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy


class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(ApplicationModule().module)


    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        lateinit var mInstance: MainApplication
        fun getAppContext() : Context{
            return mInstance.applicationContext
        }
    }

}