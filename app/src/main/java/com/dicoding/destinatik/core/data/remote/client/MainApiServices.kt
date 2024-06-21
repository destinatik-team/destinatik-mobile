// com/dicoding/destinatik/core/data/remote/client/MainApiServices.kt
package com.dicoding.destinatik.core.data.remote.client

import com.dicoding.destinatik.core.domain.model.Place
import com.dicoding.destinatik.core.domain.model.RecommendModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApiServices {
    @POST("recommend")
    suspend fun recommend(
        @Body recommend: RecommendModel
    ): Response<List<Place>> // Return a List<Place> directly
}
