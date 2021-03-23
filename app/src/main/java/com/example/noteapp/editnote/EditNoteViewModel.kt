package com.example.noteapp.editnote

import android.util.Log
import androidx.lifecycle.*
import com.example.noteapp.database.Note
import com.example.noteapp.database.NoteDatabaseDao
import kotlinx.coroutines.launch

class EditNoteViewModel(dataSource: NoteDatabaseDao, private val noteId: Long) :
    ViewModel() {

    val database = dataSource

    private val _navigateToNotesList = MutableLiveData<Boolean?>()
    val navigateToNotesList: LiveData<Boolean?>
        get() = _navigateToNotesList


    private val note = MediatorLiveData<Note>()

    fun getNote() = note

    init {
        note.addSource(database.getNoteById(noteId), note::setValue)
    }

    fun onDoneNavigating() {
        _navigateToNotesList.value = null
    }

    fun onChangeNoteText(newNoteText: String) {
        viewModelScope.launch {
            val currentNote = database.get(noteId) ?: database.getLastNote() ?: return@launch
            currentNote.noteText = newNoteText
            currentNote.noteDateMilli = System.currentTimeMillis()
            database.update(currentNote)
            _navigateToNotesList.value = true
        }
    }


}