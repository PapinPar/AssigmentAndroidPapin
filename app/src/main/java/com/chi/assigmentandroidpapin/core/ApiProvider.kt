package com.chi.assigmentandroidpapin.core

import com.chi.assigmentandroidpapin.net.RestAPI

interface ApiProvider {
    fun getApi(): RestAPI
}