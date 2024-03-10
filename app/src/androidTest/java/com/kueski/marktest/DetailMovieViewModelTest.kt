package com.kueski.marktest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.helpers.getOrAwaitValue
import com.kueski.marktest.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: DetailViewModel

    lateinit var repository: MoviesRepository
    lateinit var manager: FavouriteMoviesOpsManager

    @Mock
    lateinit var dao: FavouriteMoviesDao

    @Mock
    lateinit var api: MovieApiClientImpl

    @Test
    fun testSetFavouriteStateTrue() = runBlockingTest {
        val response = getMovie(123).toBusiness()!!.toEntity()!!
        Mockito.`when`(dao.getFavouriteMovieById(123)).thenReturn(response)
        viewModel.setFavouriteMovieState(123)
        val value = viewModel.isFavouriteMovie.getOrAwaitValue()
        assertTrue(value ?: false)
    }

    @Test
    fun testSetFavouriteStateFalse() = runBlockingTest {
        Mockito.`when`(dao.getFavouriteMovieById(123)).thenReturn(null)
        viewModel.setFavouriteMovieState(123)
        val value = viewModel.isFavouriteMovie.getOrAwaitValue()
        assertFalse(value ?: true)
    }

    @Test
    fun testOnFavouriteButtonClickedStateFavourite() = runBlockingTest {
        viewModel.isFavouriteMovie.value = true
        val movie = getMovie(123).toBusiness()!!
        viewModel.onFavouriteButtonClicked(movie)
        Mockito.verify(dao, times(1)).deleteMovie(123)
        Mockito.verify(dao, times(0)).insertMovie(movie.toEntity()!!)
        val value = viewModel.isFavouriteMovie.getOrAwaitValue()
        assertFalse(value ?: true)
    }

    @Test
    fun testOnFavouriteButtonClickedStateNotFavourite() = runBlockingTest {
        viewModel.isFavouriteMovie.value = false
        val movie = getMovie(123).toBusiness()!!
        viewModel.onFavouriteButtonClicked(movie)
        Mockito.verify(dao, times(1)).insertMovie(movie.toEntity()!!)
        Mockito.verify(dao, times(0)).deleteMovie(123)
        val value = viewModel.isFavouriteMovie.getOrAwaitValue()
        assertTrue(value ?: false)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepository(api, dao)
        manager = FavouriteMoviesOpsManager(repository)
        viewModel = DetailViewModel(manager)
    }
}