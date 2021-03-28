package com.example.android.mymovies.utils

import com.example.android.mymovies.api.ApiService
import com.example.android.mymovies.pogo.Movie

class MovieRepository(private val api : ApiService) : BaseRepository() {

    suspend fun getPopularMovies() : MutableList<Movie>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val movieResponse = safeApiCall(
            call = {api.getMovies().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList();

    }

}