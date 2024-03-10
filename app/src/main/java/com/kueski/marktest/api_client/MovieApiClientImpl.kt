package com.kueski.marktest.api_client

import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.networking.NetworkFactory
import com.kueski.marktest.services.MovieService
import retrofit2.HttpException

open class MovieApiClientImpl(networkFactory: NetworkFactory) : MoviesApiClient {

    companion object {
        private const val MOVIE_DISCOVERY_CACHE = 5
    }

    private val movieDiscoveryService =
        networkFactory.createService(MovieService::class.java, MOVIE_DISCOVERY_CACHE)
    private val nowPlayingService = networkFactory.createService(MovieService::class.java)

    @Throws(HttpException::class)
    override suspend fun getMovieList(page: Int, sortBy: String?): MovieApiResponse =
        handleHttpError { movieDiscoveryService.getMovieList(page, sortBy) }

    @Throws(KuesiHttpException::class)
    override suspend fun getNowPlaying(page: Int, sortBy: String?): MovieApiResponse =
        handleHttpError { nowPlayingService.getNowPlayingMovies(page, sortBy = sortBy) }

    @Throws(KuesiHttpException::class)
    private suspend fun handleHttpError(block: (suspend () -> MovieApiResponse)): MovieApiResponse =
        try {
            block.invoke()
        } catch (httpException: HttpException) {
            throw KuesiHttpException(httpException)
        } catch (e: Exception) {
            //Every exception will be treated as an https exception with default 1001 code
            throw KuesiHttpException()
        }

    class KuesiHttpException(val code: Int = 1001, message: String? = null) :
        RuntimeException() {
        constructor(httpException: HttpException) : this(
            httpException.code(),
            httpException.message()
        )
    }
}


