package com.kueski.marktest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.getMovie
import com.kueski.marktest.helpers.getOrAwaitValue
import com.kueski.marktest.ui.viewmodel.FavouriteMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavouriteMovieListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: FavouriteMoviesViewModel
    lateinit var repository: MoviesRepository

    @Mock
    lateinit var dao: FavouriteMoviesDao

    @Mock
    lateinit var api: MovieApiClientImpl

    @Test
    fun testGetFavouriteMovies() = runBlockingTest {
        val response = listOf(getMovie(123).toBusiness()!!)
        Mockito.`when`(dao.getFavouriteMovies()).thenReturn(response.map { it.toEntity()!! })
        viewModel.getFavouriteMovies()
        val value = viewModel.movieList.getOrAwaitValue()
        assertEquals(response, value)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepository(api, dao)
        viewModel = FavouriteMoviesViewModel(repository)
    }
}