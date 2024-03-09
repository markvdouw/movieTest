package com.kueski.marktest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.data.entities.MovieEntity

const val DB_VERSION = 1
const val FAV_MOVIES_TABLE = "favourites_tbl"

@Database(
    entities = [MovieEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
internal abstract class KueskiDb : RoomDatabase() {
    abstract fun favouriteMoviesDao(): FavouriteMoviesDao
}


