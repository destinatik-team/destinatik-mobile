package com.dicoding.destinatik.core.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("id")
    val userId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("status")
    val status: String?
)