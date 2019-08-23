package com.chi.assigmentandroidpapin.base.activ

interface BasePresenter<T : BaseView> {

    fun bindView(view: T)

    fun unbindView()

    fun onDestroy()

}