package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FavouriteMoviesViewModel : ViewModel(), KoinComponent {
    private val moviesRepository = get<MoviesRepository>()

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    suspend fun getFavouriteMovies() {
        movieList.postValue(moviesRepository.getFavouriteMovies())
    }

}