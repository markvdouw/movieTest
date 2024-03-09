package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.ui.adapter.AdapterSortingType
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class NowPlayingViewModel : ViewModel(), KoinComponent {
    private val moviesRepository = get<MoviesRepository>()
    var movieList: LiveData<PagingData<Movie>>? = null

    init {
        getNowPlayingMovies()
    }

    fun getNowPlayingMovies(sortBy: String = AdapterSortingType.NAME.sortBy) {
        movieList = moviesRepository.getNowPlayingMovies(sortBy).cachedIn(viewModelScope)
    }
}