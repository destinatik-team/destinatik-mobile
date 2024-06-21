// MainViewModel.kt
package com.dicoding.destinatik.core.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.destinatik.core.data.remote.request.SearchRequest
import com.dicoding.destinatik.core.data.remote.response.main.SearchResponse
import com.dicoding.destinatik.core.domain.model.Place
import com.dicoding.destinatik.core.domain.model.RecommendModel
import com.dicoding.destinatik.core.domain.model.SearchModel
import com.dicoding.destinatik.core.domain.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _recommendations = MutableLiveData<List<Place>>()
    val recommendations: LiveData<List<Place>> get() = _recommendations

    private val _searchResults = MutableLiveData<List<SearchModel>>()
    val searchResults: LiveData<List<SearchModel>> get() = _searchResults

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

    fun searchPlaces(accessToken: String, text: String) {
        viewModelScope.launch {
            repository.searchPlaces(accessToken, text).enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.rows?.let {
                            _searchResults.postValue(it)
                        } ?: run {
                            _error.postValue("Error: Empty response")
                        }
                    } else {
                        _error.postValue("Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    _error.postValue("Exception: ${t.message}")
                }
            })
        }
    }
}
