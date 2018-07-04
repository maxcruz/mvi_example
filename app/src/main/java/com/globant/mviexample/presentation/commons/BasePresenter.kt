package com.globant.mviexample.presentation.commons

abstract class BasePresenter<T: BaseView> {

    abstract fun bindIntents(view: T)

    abstract fun unbindIntents()

}