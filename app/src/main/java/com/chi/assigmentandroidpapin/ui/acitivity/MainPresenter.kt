package com.chi.assigmentandroidpapin.ui.acitivity

import com.chi.assigmentandroidpapin.base.activ.BasePresenter

interface MainPresenter : BasePresenter<MainView> {

    fun getImages(offset: Int)
}