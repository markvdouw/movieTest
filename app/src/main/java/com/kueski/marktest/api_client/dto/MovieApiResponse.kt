package com.kueski.marktest.api_client.dto

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    val page: Int,
    val results: List<MovieDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)