package com.dizcoding.miniproject.data.network.jsonplaceholder.response

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null
)