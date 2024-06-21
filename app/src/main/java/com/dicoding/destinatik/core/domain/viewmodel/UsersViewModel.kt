package com.dicoding.destinatik.core.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.destinatik.core.data.remote.response.users.UserResponse
import com.dicoding.destinatik.core.domain.repository.users.UsersRepository
import kotlinx.coroutines.launch

class UsersViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val _user = MutableLiveData<UserResponse?>()
    val user: LiveData<UserResponse?> get() = _user

    fun fetchUser() {
        viewModelScope.launch {
            usersRepository.getUser {
                _user.postValue(it)
            }
        }
    }
}
