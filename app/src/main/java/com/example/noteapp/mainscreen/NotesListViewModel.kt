package com.example.noteapp.mainscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.database.Note
import com.example.noteapp.database.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesListViewModel(private val database: NoteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    val notes = database.getAllNotes()

    private val _navigateToChangeNote = MutableLiveData<Long?>()
    val navigateToChangeNote: LiveData<Long?>
        get() = _navigateToChangeNote

    fun onNavigateToChangeNote(id: Long) {
        _navigateToChangeNote.value = id
    }

    fun onChangeNoteNavigated() {
        _navigateToChangeNote.value = null
    }


    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            database.update(note)
        }
    }

    private suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            database.insert(note)
        }
    }

    private suspend fun delete(noteId: Long) {
        withContext(Dispatchers.IO) {
            database.delete(noteId)
        }
    }

    fun onCreateNewNote() {
        viewModelScope.launch {
            val newNote = Note()
            insert(newNote)
            _navigateToChangeNote.value = newNote.noteId
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    fun onDelete(noteId: Long) {
        viewModelScope.launch {
            delete(noteId)
        }
    }

}

