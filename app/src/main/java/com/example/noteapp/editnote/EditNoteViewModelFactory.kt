package com.example.noteapp.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.database.Note
import com.example.noteapp.database.NoteDatabaseDao

class EditNoteViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val noteId: Long,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(dataSource, noteId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}