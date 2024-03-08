package com.kueski.marktest.api_client

import com.kueski.marktest.api_client.dto.MovieApiResponse

interface MoviesApiClient {

    suspend fun getMovieDiscovery(page: Int, sortBy: String? = null): MovieApiResponse

    suspend fun getNowPlaying(page: Int, sortBy: String? = null): MovieApiResponse
}