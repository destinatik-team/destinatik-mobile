package com.dicoding.destinatik.core.data.remote.response.main

import com.dicoding.destinatik.core.domain.model.Place
import com.google.gson.annotations.SerializedName

data class MainResponse (
    @SerializedName("data")
    val data: List<Place>
)