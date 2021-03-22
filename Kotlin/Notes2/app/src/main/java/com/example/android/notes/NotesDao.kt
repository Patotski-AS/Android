package com.example.android.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/**
 * @Dao - объект для доступа к БД
 * @Query - запрос к БД
 * @Insert - вставка в БД
 * @Delete - удаление из БД
 *
 */
@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY dayOfWeek")
    fun getAllNotes(): ArrayList<Note>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

}