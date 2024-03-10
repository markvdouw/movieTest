package com.kueski.marktest.business.manager

import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.business.repository.MoviesRepository
import org.koin.core.component.KoinComponent

class FavouriteMoviesOpsManager(private val moviesRepository: MoviesRepository) : KoinComponent {
    /**
     * Returns if a movie is maked as favourite and therefore added in the local db
     * @param movie id
     * @return true if the movie is saved as favourite, else false
     */
    suspend fun isFavourite(id: Int): Boolean {
        return moviesRepository.getFavouriteMovieById(id) != null
    }

    /**
     * Handle favourite related operations for a movie based on the object and if is saved in the db,
     * and therefore marked as favourite or not.
     * If the movie is added in the db, it will remove it and if it isn't added, it will add it.
     * @param movie
     * @param isFavouriteMovie
     */
    suspend fun handleFavouriteMovieClicked(movie: Movie, isFavouriteMovie: Boolean) {
        if (isFavouriteMovie) {
            moviesRepository.deleteFavouriteMovie(movie.id!!)
        } else {
            moviesRepository.addFavouriteMovie(movie)
        }
    }
}