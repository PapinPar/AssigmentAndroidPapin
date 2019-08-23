package com.chi.assigmentandroidpapin.net

import com.chi.assigmentandroidpapin.net.model.ImageResponse
import kotlinx.coroutines.Deferred

class ApiManager : RestAPI {
    private var appRetrofit: RestAPI

    init {
        appRetrofit = NetManager.getRestApi()
    }

    override fun getImagesAsync(offset: Int, limit: Int): Deferred<ImageResponse> {
        return appRetrofit.getImagesAsync(offset, limit)
    }
}