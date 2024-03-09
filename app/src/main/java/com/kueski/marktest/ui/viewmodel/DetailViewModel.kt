package com.kueski.marktest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DetailViewModel : ViewModel(), KoinComponent {

    private val favouriteMoviesManager = get<FavouriteMoviesOpsManager>()
    var isFavouriteMovie: MutableLiveData<Boolean?> = MutableLiveData(null)

    suspend fun onFavouriteButtonClicked(movie: Movie) {
        favouriteMoviesManager.handleFavouriteMovieClicked(movie, isFavouriteMovie.value ?: false)
        isFavouriteMovie.postValue(!(isFavouriteMovie.value ?: true))
    }

    suspend fun setFavouriteMovieState(id: Int) {
        isFavouriteMovie.postValue(favouriteMoviesManager.isFavourite(id))
    }

}