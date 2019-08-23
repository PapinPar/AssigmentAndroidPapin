package com.chi.assigmentandroidpapin.ui.acitivity

import com.chi.assigmentandroidpapin.base.activ.BaseView
import com.chi.assigmentandroidpapin.net.model.User

interface MainView : BaseView {
    fun showImages(users: List<User>)
}