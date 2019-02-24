package com.avelozo.mychoice.contract

interface BasePresenter<in T: BaseView> {

    fun attach(view: T)
    fun detach()
}

interface BaseView{}