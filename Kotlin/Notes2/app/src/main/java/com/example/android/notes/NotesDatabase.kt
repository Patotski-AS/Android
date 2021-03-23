package com.example.android.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Add a Room database:
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=nl_NL#7
 */

@Database(entities = [Note::class], version = 1, exportSchema = false)
public abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDAO(): NotesDao

    companion object {
        const val DB_NAME = "notes2.db"

        @Volatile
        private var database: NotesDatabase? = null

        /**
         * Singleton
         */
        fun getInstance(context: Context): NotesDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    DB_NAME
                )
//                    .allowMainThreadQueries()// ТОЛЬКО для тестов, Запуск в лавном потоке
                    .build()
                database = instance
                instance
            }
        }
    }
}
