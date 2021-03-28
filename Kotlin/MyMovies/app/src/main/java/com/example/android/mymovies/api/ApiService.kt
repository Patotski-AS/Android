package com.example.android.mymovies.api

import com.example.android.mymovies.pogo.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {


    @GET("discover/movie")
    fun getMovies():Deferred<Response<MovieResponse>>


    companion object{
    private const val PARAMS_API_KEY = "api_key"
    private const val PARAMS_LANGUAGE = "language"
    private const val PARAMS_SORT_BY = "sort_by"
    private const val PARAMS_PAGE = "page"

    private const val API_KEY = "3811dffbd8bc87f702dad54554f272a9"
    private const val LANGUAGE_VALUE = "ru-RU"
    private const val SORT_BY_POPULARITY = "popularity.desc"
    private const val SORT_BY_TOP_RATED = "vote_average.desc"

    const val POPULARITY = 0
    const val TOP_RATED = 1
}

}
