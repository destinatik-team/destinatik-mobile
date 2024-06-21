package com.dicoding.destinatik.core.domain.model

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)