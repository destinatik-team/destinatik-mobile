package com.dicoding.destinatik.core.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("usernameOrEmail")
    val userOrEmail: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String
)
