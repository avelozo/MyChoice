package com.avelozo.mychoice.contract

class MainContract {
    interface View : BaseView
    abstract class Presenter : MvpPresenter<View>()
}