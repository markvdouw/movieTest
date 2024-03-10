package com.kueski.marktest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.helpers.getOrAwaitValue
import com.kueski.marktest.ui.adapter.AdapterSortingType
import com.kueski.marktest.ui.viewmodel.DetailViewModel
import com.kueski.marktest.ui.viewmodel.MovieDiscoveryViewModel
import com.kueski.marktest.ui.viewmodel.NowPlayingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
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
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDiscoveryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MovieDiscoveryViewModel

    @Mock
    lateinit var repository: MoviesRepository


    @Test
    fun testGetMovieDiscoveryIsCalled() {
        viewModel.getMoviesDiscovery(AdapterSortingType.DATE.sortBy)
        Mockito.verify(repository, times(1)).getMovieList(AdapterSortingType.DATE.sortBy)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieDiscoveryViewModel(repository)
    }
}