package com.example.android.mymovies

object AppConstants {
    const val PARAMS_LANGUAGE = "language"
    const val PARAMS_SORT_BY = "sort_by"
    const val PARAMS_PAGE = "page"

    const val LANGUAGE_VALUE = "ru-RU"
    const val SORT_BY_POPULARITY = "popularity.desc"
    const val SORT_BY_TOP_RATED = "vote_average.desc"

    const val POPULARITY = 0
    const val TOP_RATED = 2
    const val API_KEY = BuildConfig.TMDB_API_KEY

}