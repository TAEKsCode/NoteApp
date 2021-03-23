package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes_table WHERE noteId =:noteId")
    suspend fun delete(noteId: Long)

    @Query("SELECT * FROM notes_table WHERE noteId = :key")
    suspend fun get(key: Long): Note?

    @Query("DELETE FROM notes_table")
    suspend fun clear()

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC LIMIT 1")
    suspend fun getLastNote(): Note?

    @Query("SELECT * FROM notes_table WHERE noteId = :key")
    fun getNoteById(key: Long): LiveData<Note>
}