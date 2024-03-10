package com.kueski.marktest

import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    private lateinit var repository: MoviesRepository

    @Mock
    lateinit var dao: FavouriteMoviesDao

    @Mock
    lateinit var api: MovieApiClientImpl

    @Test
    fun testGetFavouriteMoviesSuccess() = runBlockingTest {
        val movie = getMovie(123).toBusiness()!!
        Mockito.`when`(dao.getFavouriteMovies()).thenReturn(
            listOf(movie.toEntity()!!)
        )
        val movies = repository.getFavouriteMovies()
        assertEquals(1, movies.size)
        assertEquals(movie, movies.firstOrNull())
    }

    @Test
    fun testGetFavouriteMovieByIdExist() = runBlockingTest {
        val movie = getMovie(123).toBusiness()!!
        Mockito.`when`(dao.getFavouriteMovieById(123)).thenReturn(
            movie.toEntity()!!
        )
        assertEquals(movie, repository.getFavouriteMovieById(123))
    }

    @Test
    fun testGetFavouriteMovieByIdNotExist() = runBlockingTest {
        Mockito.`when`(dao.getFavouriteMovieById(123)).thenReturn(null)
        assertNull(repository.getFavouriteMovieById(123))
    }

    @Test
    fun testAddFavouriteMovie() = runBlockingTest {
        val movie = getMovie(123).toBusiness()!!
        repository.addFavouriteMovie(movie)
        Mockito.verify(dao, times(1)).insertMovie(movie.toEntity()!!)
    }

    @Test
    fun testDeleteFavouriteMovie() = runBlockingTest {
        val movieId = 123
        repository.deleteFavouriteMovie(movieId)
        Mockito.verify(dao, times(1)).deleteMovie(movieId)
    }

    @Before
    fun setup() {
        repository = MoviesRepository(api, dao)
    }
}