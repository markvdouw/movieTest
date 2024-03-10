package com.kueski.marktest

import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class FavouriteManagerOpsTest {

    private lateinit var manager : FavouriteMoviesOpsManager
    private lateinit var repository : MoviesRepository

    @Mock
    lateinit var dao : FavouriteMoviesDao
    @Mock
    lateinit var api : MovieApiClientImpl

    @Test
    fun testIsFavouriteFalse() = runBlockingTest {
        given(dao.getFavouriteMovieById(123)).willReturn(null)
        assertFalse( manager.isFavourite(123))
    }

    @Test
    fun testIsFavouriteTrue() = runBlockingTest {
        given(dao.getFavouriteMovieById(123)).willReturn(getMovie(123).toBusiness()!!.toEntity())
        assertTrue( manager.isFavourite(123))
    }

    @Test
    fun testHandleFavouriteClickFavourite() = runBlockingTest {
        val movie = getMovie(123).toBusiness()!!
        manager.handleFavouriteMovieClicked(movie, isFavouriteMovie = true)
        Mockito.verify(dao, times(1)).deleteMovie(123)
        Mockito.verify(dao, times(0)).insertMovie(movie.toEntity()!!)
    }

    @Test
    fun testHandleFavouriteClickNotFavourite() = runBlockingTest {
        val movie = getMovie(123).toBusiness()!!
        manager.handleFavouriteMovieClicked(movie, isFavouriteMovie = false)
        Mockito.verify(dao, times(0)).deleteMovie(123)
        Mockito.verify(dao, times(1)).insertMovie(movie.toEntity()!!)
    }

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepository(api, dao)
        manager = FavouriteMoviesOpsManager(repository)
    }

}