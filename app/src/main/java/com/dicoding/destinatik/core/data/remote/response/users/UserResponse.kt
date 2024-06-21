package com.dicoding.destinatik.core.data.remote.response.users

import com.dicoding.destinatik.core.domain.model.UsersModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    val token: String
)
