package com.dicoding.destinatik.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class SearchRequest (
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("text")
    val text: String
)