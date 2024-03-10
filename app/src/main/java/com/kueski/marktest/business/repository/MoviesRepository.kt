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
import com.kueski.marktest.ui.features.pagination.MovieListPagingSource
import com.kueski.marktest.ui.features.pagination.NowPlayingMoviesPagingSource

open class MoviesRepository(
    private val moviesApiClient: MoviesApiClient,
    private val moviesDao: FavouriteMoviesDao
) {

    companion object {
        private const val DEFAULT_PAGE = 25
    }

    /**
     * Get movie list as a live data representation on paging data
     * @param sortBy sorting param
     * @param optional pageSize if null will set default
     * @return live data representation of a paged data movie data set
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getMovieList(sortBy: String, pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = { MovieListPagingSource(moviesApiClient, sortBy) }).liveData
    }

    /**
     * Get now playing list as a live data representation on paging data
     * @param sortBy sorting param
     * @param optional pageSize if null will set default
     * @return live data representation of a paged data movie data set
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getNowPlayingMovies(sortBy: String, pageSize: Int? = null): LiveData<PagingData<Movie>> {
        return Pager(config = getPagingConfig(pageSize),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource(
                    moviesApiClient,
                    sortBy
                )
            }).liveData
    }

    /**
     * Get favourite movies
     * @return list of movies
     */
    suspend fun getFavouriteMovies(): List<Movie> =
        moviesDao.getFavouriteMovies().map { it.toBusiness() }

    /**
     * Get favourite movie by id
     * @param movie id
     * @return movie if movie is marked as favourite, else null
     */
    suspend fun getFavouriteMovieById(movieId: Int): Movie? =
        moviesDao.getFavouriteMovieById(movieId)?.toBusiness()

    /**
     * Add a movie as a favourite movie
     * @param movie
     */
    suspend fun addFavouriteMovie(movie: Movie) {
        with(movie.toEntity()) {
            this?.let { moviesDao.insertMovie(this) }
        }
    }

    /**
     * Delete movie as a favourite one
     * @param id
     */
    suspend fun deleteFavouriteMovie(id: Int?) {
        id?.let { moviesDao.deleteMovie(it) }
    }

    private fun getPagingConfig(pageSize: Int?): PagingConfig =
        PagingConfig(
            pageSize = pageSize ?: DEFAULT_PAGE,
            maxSize = 200, enablePlaceholders = false
        )

}