package com.chi.assigmentandroidpapin.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ImageResponse(
    @SerializedName("status")
    @Expose
    var status: Boolean? = null,
    @SerializedName("message")
    @Expose
    var message: Any? = null,
    @SerializedName("data")
    @Expose
    var data: Data? = null
)