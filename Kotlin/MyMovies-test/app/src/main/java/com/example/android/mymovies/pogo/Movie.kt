package com.example.android.mymovies.pogo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.mymovies.AppConstants

@Entity(tableName = AppConstants.DB_NAME)
data class Movie(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "vote_count") var voteCount: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "original_title") var originalTitle: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "poster_path") var posterPath: String,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String,
    @ColumnInfo(name = "vote_average") var voteAverage: Double,
    @ColumnInfo(name = "release_date") var releaseDate: String
)