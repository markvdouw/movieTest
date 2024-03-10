package com.kueski.marktest

import com.kueski.marktest.api_client.dto.MovieDTO

fun getMovie(movieId: Int): MovieDTO =
    MovieDTO(
        genres = null,
        id = movieId,
        language = "en-Us",
        name = getRandomString(9),
        overview = getRandomString(60),
        popularity = 4.8f,
        poster = null,
        voteAvg = 3.5f,
        date = "2024-03-01"
    )

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}