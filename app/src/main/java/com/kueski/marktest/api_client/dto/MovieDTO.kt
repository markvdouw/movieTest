package com.kueski.marktest.api_client.dto

import com.google.gson.annotations.SerializedName
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.helpers.round

data class MovieDTO(
    @SerializedName("genre_ids") val genres: List<Int>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("original_language") val language: String?,
    @SerializedName("title") val name: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("poster_path") val poster: String?,
    @SerializedName("vote_average") val voteAvg: Float?,
    @SerializedName("release_date") val date: String?
) {

    /**
     * Validate a movie based on id and name
     * @return true if id is notnull and greater than 0 AND name is not null or empty, false in other case
     */
    fun isValid() = id != null && !name.isNullOrEmpty() && id > 0

    /**
     * Convert a MovieDTO object used to parse data from service to business internal models
     * @return movie
     */
    fun toBusiness(): Movie? {
        return if (!isValid()) {
            null
        } else {
            Movie.Builder(id!!, name!!).withGenres(genres?.joinToString(","))
                .withLanguage(language)
                .withOverview(overview)
                .withPopularity(popularity?.round(2))
                .withPoster(poster)
                .withDate(date)
                .withVoteAvg(voteAvg?.round(2)).build()
        }
    }
}