// com/dicoding/destinatik/core/domain/viewmodel/MainViewModel.kt
package com.dicoding.destinatik.core.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.destinatik.core.domain.model.Place
import com.dicoding.destinatik.core.domain.model.RecommendModel
import com.dicoding.destinatik.core.domain.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _recommendations = MutableLiveData<List<Place>>()
    val recommendations: LiveData<List<Place>> get() = _recommendations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchRecommendations(userId: Int, city: String) {
        val recommendModel = RecommendModel(userId, city)
        viewModelScope.launch {
            try {
                val response = repository.getRecommendations(recommendModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _recommendations.postValue(it)
                    } ?: run {
                        _error.postValue("Error: Empty response")
                    }
                } else {
                    _error.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _error.postValue("Exception: ${e.message}")
            }
        }
    }
}
