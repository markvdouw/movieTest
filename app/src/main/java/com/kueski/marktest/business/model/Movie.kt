package com.kueski.marktest.business.model

import com.kueski.marktest.data.entities.MovieEntity
import com.kueski.marktest.helpers.round
import java.io.Serializable

class Movie private constructor(builder: Builder) : Serializable {
    var id: Int? = null
        private set
    var name: String? = null
        private set
    var poster: String? = null
        private set
    var genres: List<Int>? = null
        private set
    var overview: String? = null
        private set
    var popularity: Float? = null
        private set
    var date: String? = null
        private set
    var language: String? = null
        private set
    var voteAvg: Float? = null
        private set

    init {
        id = builder.id
        name = builder.name
        poster = builder.poster
        genres = try {
            builder.genres?.replace(" ", "")?.split(",")?.map { it.toInt() }
        } catch (e: Exception) {
            null
        }
        overview = builder.overview
        popularity = builder.popularity?.round(2)
        date = builder.date
        language = builder.language
        voteAvg = builder.voteAvg?.round(2)
    }

    fun toEntity(): MovieEntity? {
        if (!isValid()) {
            return null
        }
        val strGenres = genres?.joinToString(",")
        return MovieEntity(
            id!!,
            name!!,
            poster,
            strGenres,
            overview,
            popularity?.round(2),
            date,
            language,
            voteAvg?.round(2)
        )
    }

    fun isValid() = id != null && !name.isNullOrEmpty() && id!! > 0

    class Builder() {
        constructor(id: Int, name: String) : this() {
            this.id = id
            this.name = name
        }

        var id: Int? = null
            private set
        var name: String? = null
            private set
        var poster: String? = null
            private set
        var genres: String? = null
            private set
        var overview: String? = null
            private set
        var popularity: Float? = null
            private set
        var date: String? = null
            private set
        var language: String? = null
            private set
        var voteAvg: Float? = null
            private set
        var favourite: Boolean = false
            private set

        fun withPopularity(popularity: Float?): Builder {
            this.popularity = popularity
            return this
        }

        fun withGenres(genres: String?): Builder {
            this.genres = genres
            return this
        }

        fun withFavourite(favourite: Boolean): Builder {
            this.favourite = favourite
            return this
        }

        fun withOverview(overview: String?): Builder {
            this.overview = overview
            return this
        }

        fun withDate(date: String?): Builder {
            this.date = date
            return this
        }

        fun withLanguage(language: String?): Builder {
            this.language = language
            return this
        }

        fun withPoster(poster: String?): Builder {
            this.poster = poster
            return this
        }

        fun withVoteAvg(voteAvg: Float?): Builder {
            this.voteAvg = voteAvg
            return this
        }

        fun build(): Movie = Movie(this)
    }
}