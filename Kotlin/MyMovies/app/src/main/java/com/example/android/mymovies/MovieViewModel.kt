package com.example.android.mymovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mymovies.api.ApiFactory
import com.example.android.mymovies.pogo.Movie
import com.example.android.mymovies.utils.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MovieViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(ApiFactory.apiService)


    val popularMoviesLiveData = MutableLiveData<MutableList<Movie>?>()

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}
