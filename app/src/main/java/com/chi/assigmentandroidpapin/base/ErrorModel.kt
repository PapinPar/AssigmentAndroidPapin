package com.woofy.ui.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("errors")
    @Expose
    var errors: List<Error>,
    @SerializedName("status")
    @Expose
    var status: Int
)