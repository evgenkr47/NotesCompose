package com.example.notescompose.notes_layers.data.database

import androidx.room.*
import com.example.notescompose.notes_layers.domain.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<Note>>


    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}