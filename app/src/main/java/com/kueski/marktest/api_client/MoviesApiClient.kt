package com.kueski.marktest.api_client

import com.kueski.marktest.api_client.dto.MovieApiResponse

interface MoviesApiClient {

    /**
     * Get Movie list paginated and sorted (optional)
     * @param page  starting from 1
     * @param sortBy based on [AdapterSortingType] can either be [AdapterSortingType.DATE] or
     * [AdapterSortingType.NAME], in both cases is an ASC sorting
     * @return movie api wrapper dto object
     */
    suspend fun getMovieList(page: Int, sortBy: String? = null): MovieApiResponse

    /**
     * Get Now playing movies paginated and sorted (optional)
     * @param page  starting from 1
     * @param sortBy based on [AdapterSortingType] can either be [AdapterSortingType.DATE] or
     * [AdapterSortingType.NAME], in both cases is an ASC sorting
     * @return movie api wrapper dto object
     */
    suspend fun getNowPlaying(page: Int, sortBy: String? = null): MovieApiResponse
}