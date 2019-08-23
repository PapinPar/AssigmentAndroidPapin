package com.chi.assigmentandroidpapin.net

import com.chi.assigmentandroidpapin.net.model.ImageResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {
    @GET("api/users")
    fun getImagesAsync(@Query("offset") offset: Int, @Query("limit") limit: Int): Deferred<ImageResponse>
}