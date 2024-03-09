package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.helpers.toMutableLiveData
import com.kueski.marktest.ui.adapter.AdapterSortingType
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MovieDiscoveryViewModel : ViewModel(), KoinComponent {
    private val moviesRepository = get<MoviesRepository>()
    var movieList: LiveData<PagingData<Movie>>? = null

    init {
        getMoviesDiscovery()
    }

    fun getMoviesDiscovery(sortBy: String = AdapterSortingType.DATE.sortBy) {
        movieList = moviesRepository.getMoviesDiscovery(sortBy).cachedIn(viewModelScope)
    }

}