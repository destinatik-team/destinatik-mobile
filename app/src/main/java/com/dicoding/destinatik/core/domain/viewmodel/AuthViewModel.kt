package com.dicoding.destinatik.core.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.destinatik.core.data.remote.response.auth.LoginResponse
import com.dicoding.destinatik.core.data.remote.response.auth.RegisterResponse
import com.dicoding.destinatik.core.domain.repository.auth.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> = _loginResult

    private val _registerResult = MutableLiveData<RegisterResponse?>()
    val registerResult: LiveData<RegisterResponse?> = _registerResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun login(usernameOrEmail: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            repository.login(usernameOrEmail, password) { response ->
                _loading.value = false
                if (response != null) {
                    _loginResult.postValue(response)
                } else {
                    _error.postValue("Login failed: Invalid username or password.")
                }
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            repository.register(username, email, password) { response ->
                _loading.value = false
                if (response != null) {
                    _registerResult.postValue(response)
                } else {
                    _error.postValue("Registration failed: Try again.")
                }
            }
        }
    }
}
