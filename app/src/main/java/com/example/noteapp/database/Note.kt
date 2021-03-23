package com.example.noteapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "note_time_milli")
    var noteDateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "note_text")
    var noteText: String = ""
)