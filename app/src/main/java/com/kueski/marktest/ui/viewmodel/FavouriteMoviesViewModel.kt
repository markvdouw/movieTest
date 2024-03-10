package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository

class FavouriteMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    suspend fun getFavouriteMovies() {
        movieList.postValue(moviesRepository.getFavouriteMovies())
    }

}