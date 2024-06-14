package com.dicoding.destinatik.core.data.remote.response.users

import com.dicoding.destinatik.core.domain.model.UsersModel

data class UserResponse(
    val rows: List<UsersModel>
)
