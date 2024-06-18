package com.dicoding.destinatik.core.data.remote.client

import com.dicoding.destinatik.core.data.remote.response.auth.LoginResponse
import com.dicoding.destinatik.core.data.remote.response.auth.RegisterResponse
import com.dicoding.destinatik.core.domain.model.AuthModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("usernameOrEmail") usernameOrEmail: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

}