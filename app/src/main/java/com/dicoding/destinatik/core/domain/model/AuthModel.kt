package com.dicoding.destinatik.core.domain.model

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("status")
    val status: String
)
