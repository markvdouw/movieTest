package com.kueski.marktest.ui.favourite_movies

import android.net.http.HttpException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.data.FavouriteMoviesDao
import com.kueski.marktest.services.MovieService
import kotlinx.coroutines.delay
import java.io.IOException

@ExperimentalPagingApi
class FavouriteMoviesPagingSource(val favouriteMoviesDao: FavouriteMoviesDao) :
    PagingSource<Int, Movie>() {
    companion object {
        private const val STARTING_PAGE = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val data = try {
                favouriteMoviesDao.getFavouriteMovies(params.loadSize, page * params.loadSize)
            } catch (e: Exception) {
                emptyList()
            }
            if (page != 0) {
                //simulate page loading 1 sec.
                delay(1000)
            }
            LoadResult.Page(
                data.map { it.toBusiness() },
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}