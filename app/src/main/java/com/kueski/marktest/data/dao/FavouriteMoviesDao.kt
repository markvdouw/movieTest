package com.kueski.marktest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kueski.marktest.data.entities.MovieEntity

@Dao
interface FavouriteMoviesDao {

    @Query("DELETE FROM favourites_tbl WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM favourites_tbl")
    suspend fun getFavouriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM favourites_tbl WHERE id = :id")
    suspend fun getFavouriteMovieById(id: Int): MovieEntity?
}