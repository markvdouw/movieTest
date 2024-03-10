package com.kueski.marktest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.api_client.dto.MovieDTO
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.ui.features.pagination.MovieListPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
abstract class BasePagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: MovieApiClientImpl

    private fun getMovies(movieId: Int) = mutableListOf(getMovie(movieId))

    fun getMovieResponse(movieId: Int = (Math.random() * 10000).toInt()) =
        MovieApiResponse(1, getMovies(movieId), totalPages = 1, totalResults = 1)

    fun getExpectedPagingResult(
        movieResponse: MovieApiResponse,
        page: Int = 1
    ): PagingSource.LoadResult.Page<Int, Movie> {
        return PagingSource.LoadResult.Page(
            data = movieResponse.results.map { it.toBusiness()!! },
            prevKey = if (page == 1) null else page - 1,
            nextKey = page + 1
        )
    }

    fun getExpectedException(exception: Exception): PagingSource.LoadResult.Error<Int, Movie> {
        return PagingSource.LoadResult.Error(exception)
    }

    protected suspend fun <T : PagingSource<Int, Movie>> getPaginationResult(
        pagingSource: T,
        page: Int
    ): PagingSource.LoadResult.Page<Int, Movie> {
        return pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = page,
                loadSize = 1,
                placeholdersEnabled = false
            )
        ) as PagingSource.LoadResult.Page<Int, Movie>
    }

    fun setupParent() {
        MockitoAnnotations.initMocks(this)
    }
}