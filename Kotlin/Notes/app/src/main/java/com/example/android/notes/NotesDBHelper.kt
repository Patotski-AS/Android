package com.example.android.notes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * onCreate - вызывается при создании БД
 * onUpgrade - обновление БД (удаляем старую - вставляем новую)
 * execSQL - обращаемся к БД
 */
class NotesDBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "notes.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NotesContract.NotesEntry.CREATE_COMMAND)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(NotesContract.NotesEntry.DROP_COMMAND)
        onCreate(db)
    }
}