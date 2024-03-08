package com.kueski.marktest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteMoviesDao {

    @Query("DELETE FROM favourites_tbl WHERE id = :id")
    suspend fun deleteMovie(id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie : MovieEntity)

    @Query("SELECT * FROM favourites_tbl ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getFavouriteMovies(limit : Int, offset : Int): List<MovieEntity>
}