package com.kueski.marktest.business.manager

import com.kueski.marktest.api_client.dto.MovieDTO
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.data.entities.MovieEntity
import org.junit.Assert
import org.junit.Test

class MovieObjectMappingTest {

    companion object {
        private const val ID = 123
        private const val TITLE = "Title test"
        private const val POSTER = "/1203h1jnoasd.jpg"
        private const val GENRES = "1,123,53,5"
        private const val OVERVIEW = "This is a short description of the movie"
        private const val POPULARITY = 4.5f
        private const val DATE = "2024-03-02"
        private const val LANGUAGE = "en-US"
        private const val VOTE_AVG = 3.7f

    }

    val entity =
        MovieEntity(ID, TITLE, POSTER, GENRES, OVERVIEW, POPULARITY, DATE, LANGUAGE, VOTE_AVG)
    val movie = Movie.Builder(ID, TITLE).withDate(DATE).withGenres(GENRES).withLanguage(LANGUAGE)
        .withOverview(
            OVERVIEW
        ).withPopularity(POPULARITY).withPoster(POSTER).withVoteAvg(VOTE_AVG).build()
    val movieDto = MovieDTO(
        listOf(1, 123, 53, 5),
        ID,
        LANGUAGE,
        TITLE,
        OVERVIEW,
        POPULARITY,
        POSTER,
        VOTE_AVG,
        DATE
    )

    @Test
    fun testEntityToBusiness() {
        Assert.assertEquals(movie, entity.toBusiness())
    }

    @Test
    fun testBusinessToEntity() {
        Assert.assertEquals(entity, movie.toEntity())
    }

    @Test
    fun testDTOtoBusiness() {
        Assert.assertEquals(movie, movieDto.toBusiness())
    }
}