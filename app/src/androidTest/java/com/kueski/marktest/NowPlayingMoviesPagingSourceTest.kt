package com.kueski.marktest

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.ui.features.pagination.NowPlayingMoviesPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
/**
 * This class checks the internal loading mechanism and logic within the PagingSource
 * defined for Now playing movies which internally uses the [MovieApiClientImpl] and is called
 * from the [MoviesRepository]
 */
class NowPlayingMoviesPagingSourceTest : BasePagingSourceTest() {

    lateinit var nowPlayingMoviesPagingSource: NowPlayingMoviesPagingSource

    @Test
    fun testSuccessResponse() = runBlockingTest {
        val movieResponse = getMovieResponse(24)
        BDDMockito.given(api.getNowPlaying(1, "name")).willReturn(movieResponse)
        assertEquals(
            getExpectedPagingResult(movieResponse, 1).data,
            getPaginationResult(nowPlayingMoviesPagingSource, 1).data
        )
    }

    @Test
    fun testFailResponse() = runBlockingTest {
        val exception = MockitoException("test")
        BDDMockito.given(api.getNowPlaying(1, "name")).willThrow(exception)
        val response = nowPlayingMoviesPagingSource.load(
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
        BDDMockito.given(api.getNowPlaying(1, "name")).willReturn(movieResponsePageOne)
        BDDMockito.given(api.getNowPlaying(2, "name")).willReturn(movieResponsePageTwo)
        assertEquals(
            getExpectedPagingResult(movieResponsePageTwo, 2).data,
            getPaginationResult(nowPlayingMoviesPagingSource, 2).data
        )
        assertNotEquals(
            getExpectedPagingResult(movieResponsePageTwo, 2).data,
            getPaginationResult(nowPlayingMoviesPagingSource, 1).data
        )
    }

    @Before
    fun setup() {
        super.setupParent()
        nowPlayingMoviesPagingSource = NowPlayingMoviesPagingSource(api, "name")
    }
}


