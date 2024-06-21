// com/dicoding/destinatik/core/domain/repository/MainRepository.kt
package com.dicoding.destinatik.core.domain.repository

import com.dicoding.destinatik.core.data.remote.client.MainApiServices
import com.dicoding.destinatik.core.domain.model.Place
import com.dicoding.destinatik.core.domain.model.RecommendModel
import retrofit2.Response

class MainRepository(private val mainApiServices: MainApiServices) {
    suspend fun getRecommendations(recommendModel: RecommendModel): Response<List<Place>> {
        return mainApiServices.recommend(recommendModel)
    }
}
