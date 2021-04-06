package com.example.android.mymovies

object AppConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val PARAMS_LANGUAGE = "language"
    const val PARAMS_SORT_BY = "sort_by"
    const val PARAMS_PAGE = "page"

    const val LANGUAGE_VALUE = "ru-RU"
    const val SORT_BY_POPULARITY = "popularity.desc"
    const val SORT_BY_TOP_RATED = "vote_average.desc"
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
    const val SMALL_POSTER_SIZE = "w185"
    const val BIG_POSTER_SIZE = "w780"


    const val POPULARITY = 0
    const val TOP_RATED = 2
    const val API_KEY = BuildConfig.TMDB_API_KEY

    const val DB_NAME = "movies"

}