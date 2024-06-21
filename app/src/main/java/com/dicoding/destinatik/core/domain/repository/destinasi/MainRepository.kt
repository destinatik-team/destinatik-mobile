// MainRepository.kt
package com.dicoding.destinatik.core.domain.repository

import com.dicoding.destinatik.core.data.remote.client.ApiServices
import com.dicoding.destinatik.core.data.remote.client.MainApiServices
import com.dicoding.destinatik.core.data.remote.request.SearchRequest
import com.dicoding.destinatik.core.data.remote.response.main.SearchResponse
import com.dicoding.destinatik.core.domain.model.Place
import com.dicoding.destinatik.core.domain.model.RecommendModel
import retrofit2.Call
import retrofit2.Response

class MainRepository(
    private val mainApiServices: MainApiServices,
    private val apiServices: ApiServices
) {
    suspend fun getRecommendations(recommendModel: RecommendModel): Response<List<Place>> {
        return mainApiServices.recommend(recommendModel)
    }

    fun searchPlaces(accessToken: String, text: String): Call<SearchResponse> {
        return apiServices.search(accessToken, text)
    }
}
