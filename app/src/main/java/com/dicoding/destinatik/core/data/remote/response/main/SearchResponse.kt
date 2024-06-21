package com.dicoding.destinatik.core.data.remote.response.main

import com.dicoding.destinatik.core.domain.model.SearchModel

data class SearchResponse(
    val rows: List<SearchModel>
)
