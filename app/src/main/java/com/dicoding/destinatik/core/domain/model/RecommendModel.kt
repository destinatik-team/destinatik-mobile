package com.dicoding.destinatik.core.domain.model

import com.google.gson.annotations.SerializedName

data class RecommendModel(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("city")
    val city: String,
)
