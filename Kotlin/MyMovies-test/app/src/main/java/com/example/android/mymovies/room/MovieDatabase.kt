package com.example.android.mymovies.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.pogo.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDAO: MovieDAO

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null
    }

    fun getInstance(context: Context): MovieDatabase {
        synchronized(this) {
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    AppConstants.DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }


                return instance
        }
    }
}