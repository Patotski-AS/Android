package com.example.android.mymovies.pogo

data class Movie(
    var id: Int,
    var vote_count: Int,
    var title: String,
    var original_title: String,
    var overview: String,
    var poster_path: String,
    var backdrop_path: String,
    var vote_average: Double,
    var release_date: String
)