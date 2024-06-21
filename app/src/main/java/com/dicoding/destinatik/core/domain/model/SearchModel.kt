package com.dicoding.destinatik.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    val location: Location,
    @SerializedName("place_id")
    val placeId: String,
    val photos: List<Photo>
) : Serializable
