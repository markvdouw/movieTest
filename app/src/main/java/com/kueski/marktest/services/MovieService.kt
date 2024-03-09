package com.kueski.marktest.services

import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.helpers.getNowMinus
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    //BASE: https://api.themoviedb.org/3/
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String? = null
    ): MovieApiResponse

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&with_release_type=2|3")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("release_date.lte") maxDate: String = getNowMinus(0),
        @Query("release_date.gte") minDate: String = getNowMinus(7),
        @Query("sort_by") sortBy: String? = null
    ): MovieApiResponse

}