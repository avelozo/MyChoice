package com.avelozo.mychoice.contract

class MainContract {
    interface View : BaseView{
        fun showErrorMessage()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract  fun initCategories()

    }
}