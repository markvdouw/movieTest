package com.kueski.marktest.business.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.kueski.marktest.api_client.MoviesApiClient
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.data.FavouriteMoviesDao
import com.kueski.marktest.ui.discovery.MovieDiscoveryPagingSource
import com.kueski.marktest.ui.favourite_movies.FavouriteMoviesPagingSource

class MoviesRepository(
    private val moviesApiClient: MoviesApiClient,
    private val moviesDao: FavouriteMoviesDao
) {

    companion object {
        private const val DEFAULT_PAGE = 25
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getMoviesDiscovery(pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { MovieDiscoveryPagingSource(moviesApiClient) }).liveData
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getNowPlayingMovies(pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { MovieDiscoveryPagingSource(moviesApiClient) }).liveData
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getFavouriteMovies(pageSize: Int = 30): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { FavouriteMoviesPagingSource(moviesDao) }).liveData

//        return try {
//            moviesDao.getFavouriteMovies(limit, offset).map { it.toBusiness() }
//        } catch (e: Exception) {
//            emptyList()
//        }
    }

    suspend fun markFavourite(movie: Movie): Movie {
        if (movie.favourite) {
            deleteFromFavouriteTable(movie.id)
        } else {
            addToFavouriteTable(movie)
        }
        return movie.markFavourite(!movie.favourite)
    }

    private suspend fun addToFavouriteTable(movie: Movie) {
        with(movie.markFavourite(true).toEntity()) {
            this?.let { moviesDao.insertMovie(this) }
        }
    }

    private suspend fun deleteFromFavouriteTable(id: Int?) {
        id?.let { moviesDao.deleteMovie(it) }
    }

    private fun getPagingConfig(pageSize: Int?): PagingConfig =
        PagingConfig(
            pageSize = pageSize ?: DEFAULT_PAGE,
            maxSize = 200, enablePlaceholders = false
        )

}