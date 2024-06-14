package com.dicoding.destinatik.core.data.remote.client

import com.dicoding.destinatik.core.data.remote.response.auth.LoginResponse
import com.dicoding.destinatik.core.data.remote.response.auth.RegisterResponse
import com.dicoding.destinatik.core.domain.model.AuthModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("auth/login")
    fun login(
        @Body user: AuthModel
    ): Call<LoginResponse>

    @POST("auth/register")
    fun register(
        @Body user: AuthModel
    ): Call<RegisterResponse>
}