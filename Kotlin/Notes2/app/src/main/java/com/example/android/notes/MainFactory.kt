package com.example.android.notes

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFactory(
    private val application: Application,
    private val database: NotesDatabase = NotesDatabase.getInstance(application),
    private val notes: LiveData<List<Note>> = database.notesDAO().getAllNotes()

) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MainViewModel(application) as T
    }

}