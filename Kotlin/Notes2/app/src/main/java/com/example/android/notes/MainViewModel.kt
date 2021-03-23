package com.example.android.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val database: NotesDatabase = NotesDatabase.getInstance(application),
    private val notes: LiveData<List<Note>> = database.notesDAO().getAllNotes()
) : AndroidViewModel(application) {

    fun getNotes() = notes

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            database.notesDAO().insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            database.notesDAO().deleteNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            database.notesDAO().deleteAllNotes()
        }
    }
}

