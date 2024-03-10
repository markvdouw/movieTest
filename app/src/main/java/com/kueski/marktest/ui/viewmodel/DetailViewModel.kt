package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import com.kueski.marktest.business.model.Movie

class DetailViewModel(private val favouriteManager: FavouriteMoviesOpsManager) : ViewModel() {

    var isFavouriteMovie: MutableLiveData<Boolean?> = MutableLiveData(null)

    suspend fun onFavouriteButtonClicked(movie: Movie) {
        favouriteManager.handleFavouriteMovieClicked(movie, isFavouriteMovie.value ?: false)
        isFavouriteMovie.postValue(!(isFavouriteMovie.value ?: true))
    }

    suspend fun setFavouriteMovieState(id: Int) {
        isFavouriteMovie.postValue(favouriteManager.isFavourite(id))
    }

}