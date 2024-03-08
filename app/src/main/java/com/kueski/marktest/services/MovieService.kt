package com.kueski.marktest.services

import com.kueski.marktest.api_client.dto.MovieApiResponse
import com.kueski.marktest.helpers.formatDate
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
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
        @Query("sort_by") sortBy: String? = null,
        @Query("release_date.lte") maxDate : String= System.currentTimeMillis().formatDate()
    ): MovieApiResponse

}