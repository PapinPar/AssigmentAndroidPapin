package com.chi.assigmentandroidpapin.core

import android.app.Application
import com.chi.assigmentandroidpapin.net.ApiManager
import com.chi.assigmentandroidpapin.net.RestAPI

class CoreApp : Application(), ApiProvider {


    private lateinit var netManager: RestAPI

    override fun onCreate() {
        super.onCreate()

        netManager = ApiManager()
        ResourcesRepository.with(this)
    }

    override fun getApi(): RestAPI {
        return netManager
    }
}