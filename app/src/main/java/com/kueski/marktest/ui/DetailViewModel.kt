package com.kueski.marktest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DetailViewModel : ViewModel(), KoinComponent {

    private val moviesRepository = get<MoviesRepository>()

    suspend fun addFavourite(movie: Movie): Movie {
        return moviesRepository.markFavourite(movie)
    }
}