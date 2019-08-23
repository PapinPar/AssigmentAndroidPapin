package com.chi.assigmentandroidpapin.net.model

data class MappedData(
    var isHeader: Boolean,
    var name: String = "",
    var avatar: String = "",
    var url: List<String>? = null
)