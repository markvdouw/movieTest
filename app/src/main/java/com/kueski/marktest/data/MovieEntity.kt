package com.kueski.marktest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kueski.marktest.business.model.Movie

@Entity(tableName = FAV_MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val poster: String?,
    val genres: String?,
    val overview: String?,
    val popularity: Float?,
    val date: String?,
    val language: String?,
    val voteAvg: Float?,
    val favourite: Boolean = false
) {
    fun toBusiness(): Movie =
        Movie.Builder(id, name).withPoster(poster).withGenres(genres).withOverview(overview)
            .withPopularity(popularity).withDate(date).withLanguage(language).withVoteAvg(voteAvg)
            .withFavourite(favourite)
            .build()

}
