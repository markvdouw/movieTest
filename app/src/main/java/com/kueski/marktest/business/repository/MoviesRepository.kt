package com.kueski.marktest.business.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.kueski.marktest.api_client.MoviesApiClient
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.ui.features.pagination.MovieDiscoveryPagingSource
import com.kueski.marktest.ui.features.pagination.NowPlayingMoviesPagingSource

class MoviesRepository(
    private val moviesApiClient: MoviesApiClient,
    private val moviesDao: FavouriteMoviesDao
) {

    companion object {
        private const val DEFAULT_PAGE = 25
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getMoviesDiscovery(sortBy : String, pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { MovieDiscoveryPagingSource(moviesApiClient, sortBy) }).liveData
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getNowPlayingMovies(sortBy: String, pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { NowPlayingMoviesPagingSource(moviesApiClient, sortBy) }).liveData
    }

    suspend fun getFavouriteMovies(): List<Movie> =
        moviesDao.getFavouriteMovies().map { it.toBusiness() }

    suspend fun getFavouriteMovieById(movieId: Int): Movie? =
        moviesDao.getFavouriteMovieById(movieId)?.toBusiness()

    suspend fun addFavouriteMovie(movie: Movie) {
        with(movie.toEntity()) {
            this?.let { moviesDao.insertMovie(this) }
        }
    }

    suspend fun deleteFavouriteMovie(id: Int?) {
        id?.let { moviesDao.deleteMovie(it) }
    }

    private fun getPagingConfig(pageSize: Int?): PagingConfig =
        PagingConfig(
            pageSize = pageSize ?: DEFAULT_PAGE,
            maxSize = 200, enablePlaceholders = false
        )

}