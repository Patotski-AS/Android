package com.example.android.mymovies.api

import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.pogo.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query(AppConstants.PARAMS_LANGUAGE) language: String = AppConstants.LANGUAGE_VALUE,
        @Query(AppConstants.PARAMS_SORT_BY) sorted: String = AppConstants.SORT_BY_POPULARITY,
        @Query(AppConstants.PARAMS_PAGE) page: String = AppConstants.TOP_RATED.toString()
    ): Deferred<Response<MovieResponse>>

}
