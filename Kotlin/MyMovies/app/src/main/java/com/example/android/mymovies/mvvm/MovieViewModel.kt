package com.example.android.mymovies.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.api.ApiFactory
import com.example.android.mymovies.api.ApiService
import com.example.android.mymovies.pogo.Movie
import com.example.android.mymovies.mvvm.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MovieViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(ApiFactory.apiService)


    val popularMoviesLiveData = MutableLiveData<MutableList<Movie>?>()

    fun fetchMovies(
        language: String = AppConstants.LANGUAGE_VALUE,
        sorted: String = AppConstants.SORT_BY_POPULARITY,
        page: String = AppConstants.TOP_RATED.toString()
    ) {
        scope.launch {
            val popularMovies = repository.getPopularMovies(language, sorted, page)
            popularMoviesLiveData.postValue(popularMovies)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}
