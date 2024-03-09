package com.kueski.marktest.ui.features.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kueski.marktest.api_client.MoviesApiClient
import com.kueski.marktest.business.model.Movie


@ExperimentalPagingApi
class MovieDiscoveryPagingSource(private val moviesApiClient: MoviesApiClient) :
    PagingSource<Int, Movie>() {
    companion object {
        private const val STARTING_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = moviesApiClient.getMovieDiscovery(page, null)
            val nextKey = if (response.results.isEmpty()) null else page + 1
            val prevKey = if (page == STARTING_PAGE) null else page - 1
            LoadResult.Page(
                response.results.map { it.toBusiness()!! },
                prevKey = prevKey,
                nextKey = nextKey
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