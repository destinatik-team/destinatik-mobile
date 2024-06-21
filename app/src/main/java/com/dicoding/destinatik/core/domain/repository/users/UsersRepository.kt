package com.dicoding.destinatik.core.domain.repository.users

import com.dicoding.destinatik.core.data.remote.client.ApiServices
import com.dicoding.destinatik.core.data.remote.response.users.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepository(private val apiServices: ApiServices) {

    fun getUser(callback: (UserResponse?) -> Unit) {
        apiServices.getUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
