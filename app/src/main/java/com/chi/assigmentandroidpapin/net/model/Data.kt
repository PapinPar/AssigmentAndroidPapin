package com.chi.assigmentandroidpapin.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("users")
    @Expose
    var users: List<User>? = null,
    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null
)