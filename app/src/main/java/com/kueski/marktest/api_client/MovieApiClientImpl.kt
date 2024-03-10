package com.kueski.marktest.api_client

import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.networking.NetworkFactory
import com.kueski.marktest.services.MovieService

open class MovieApiClientImpl(networkFactory: NetworkFactory) : MoviesApiClient {

    companion object {
        private const val MOVIE_DISCOVERY_CACHE = 5
    }

    private val movieDiscoveryService =
        networkFactory.createService(MovieService::class.java, MOVIE_DISCOVERY_CACHE)
    private val nowPlayingService = networkFactory.createService(MovieService::class.java)

    override suspend fun getMovieList(page: Int, sortBy: String?): MovieApiResponse =
        movieDiscoveryService.getMovieList(page, sortBy)

    override suspend fun getNowPlaying(page: Int, sortBy: String?): MovieApiResponse =
        nowPlayingService.getNowPlayingMovies(page, sortBy = sortBy)
}