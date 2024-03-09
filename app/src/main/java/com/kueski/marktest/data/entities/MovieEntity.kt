package com.kueski.marktest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.data.FAV_MOVIES_TABLE
import com.kueski.marktest.helpers.round

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
    val voteAvg: Float?
) {
    fun toBusiness(): Movie =
        Movie.Builder(id, name).withPoster(poster).withGenres(genres).withOverview(overview)
            .withPopularity(popularity?.round(2)).withDate(date).withLanguage(language)
            .withVoteAvg(voteAvg?.round(2))
            .build()

}
