package com.dicoding.destinatik.core.data.remote.client

import com.dicoding.destinatik.core.data.remote.request.SearchRequest
import com.dicoding.destinatik.core.data.remote.response.auth.LoginResponse
import com.dicoding.destinatik.core.data.remote.response.auth.RegisterResponse
import com.dicoding.destinatik.core.data.remote.response.main.SearchResponse
import com.dicoding.destinatik.core.data.remote.response.users.UserResponse
import com.dicoding.destinatik.core.domain.model.AuthModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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

    @GET("profile")
    fun getUser(
    ): Call<UserResponse>

    @FormUrlEncoded
    @POST("maps/search")
    fun search(
        @Field("access_token") accessToken: String,
        @Field("text") text: String
    ): Call<SearchResponse>
}