package com.kueski.marktest.business.manager

import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import org.koin.core.component.KoinComponent

class FavouriteMoviesOpsManager(private val moviesRepository: MoviesRepository) : KoinComponent {
    suspend fun isFavourite(id: Int): Boolean {
        return moviesRepository.getFavouriteMovieById(id) != null
    }
    suspend fun handleFavouriteMovieClicked(movie: Movie, isFavouriteMovie: Boolean) {
        if (isFavouriteMovie) {
            moviesRepository.deleteFavouriteMovie(movie.id!!)
        } else {
            moviesRepository.addFavouriteMovie(movie)
        }
    }
}