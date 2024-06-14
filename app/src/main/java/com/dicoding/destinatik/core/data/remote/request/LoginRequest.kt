package com.dicoding.destinatik.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("usernameOrEmail")
    val userOrEmail: String,
    @SerializedName("password")
    val password: String
)

