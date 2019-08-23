package com.chi.assigmentandroidpapin.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("items")
    @Expose
    var items: List<String>? = null
)
