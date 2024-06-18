package com.dicoding.destinatik.core.domain.repository.auth

import com.dicoding.destinatik.core.data.remote.client.ApiServices
import com.dicoding.destinatik.core.data.remote.response.auth.LoginResponse
import com.dicoding.destinatik.core.data.remote.response.auth.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val apiServices: ApiServices) {

    fun login(usernameOrEmail: String, password: String, callback: (LoginResponse?) -> Unit) {
        apiServices.login(usernameOrEmail, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun register(username: String, email: String, password: String, callback: (RegisterResponse?) -> Unit) {
        apiServices.register(username, email, password).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
