package com.kueski.marktest.ui.favourite_movies

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

class FavouriteMoviesViewModel : ViewModel(), KoinComponent {
    private val moviesRepository = get<MoviesRepository>()

    var movieList: LiveData<PagingData<Movie>> = getFavouriteMovies()

    fun getFavouriteMovies(): LiveData<PagingData<Movie>> =
        moviesRepository.getFavouriteMovies().cachedIn(viewModelScope)


}