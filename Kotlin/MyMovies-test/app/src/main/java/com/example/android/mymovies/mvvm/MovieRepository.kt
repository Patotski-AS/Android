package com.example.android.mymovies.mvvm

import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.api.ApiService
import com.example.android.mymovies.pogo.Movie
import retrofit2.http.Query

class MovieRepository(private val api: ApiService) : BaseRepository() {

    suspend fun getPopularMovies(
        language: String = AppConstants.LANGUAGE_VALUE,
        sorted: String = AppConstants.SORT_BY_POPULARITY,
        page: String = AppConstants.TOP_RATED.toString()
    ): MutableList<Movie>? {
        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val movieResponse = safeApiCall(
            call = { api.getMovies(language,sorted,page).await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        return movieResponse?.results?.toMutableList();
    }

}