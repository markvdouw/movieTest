package com.kueski.marktest

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.ui.features.pagination.MovieListPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
/**
 * This class checks the internal loading mechanism and logic within the PagingSource
 * defined for MovieList which internally uses the [MovieApiClientImpl] and is called
 * from the [MoviesRepository]
 */
class MovieListPagingSourceTest : BasePagingSourceTest() {

    lateinit var movieListPagingSource: MovieListPagingSource

    @Test
    fun testSuccessResponse() = runBlockingTest {
        val movieResponse = getMovieResponse(24)
        given(api.getMovieList(1, "name")).willReturn(movieResponse)
        assertEquals(
            getExpectedPagingResult(movieResponse, 1).data,
            getPaginationResult(movieListPagingSource, 1).data
        )
    }

    @Test
    fun testFailResponse() = runBlockingTest {
        val exception = MockitoException("test")
        given(api.getMovieList(1, "name")).willThrow(exception)
        val response = movieListPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 1,
                placeholdersEnabled = false
            )
        ) as PagingSource.LoadResult.Error<Int, Movie>
        assertEquals(getExpectedException(exception).throwable, response.throwable)
    }

    @Test
    fun testSuccessResponseDifferentPage() = runBlockingTest {
        val movieResponsePageOne = getMovieResponse(24)
        val movieResponsePageTwo = getMovieResponse(12)
        given(api.getMovieList(1, "name")).willReturn(movieResponsePageOne)
        given(api.getMovieList(2, "name")).willReturn(movieResponsePageTwo)
        assertEquals(
            getExpectedPagingResult(movieResponsePageTwo, 2).data,
            getPaginationResult(movieListPagingSource, 2).data
        )
        assertNotEquals(
            getExpectedPagingResult(movieResponsePageTwo, 2).data,
            getPaginationResult(movieListPagingSource, 1).data
        )
    }

    @Before
    fun setup() {
        super.setupParent()
        movieListPagingSource = MovieListPagingSource(api, "name")
    }
}
