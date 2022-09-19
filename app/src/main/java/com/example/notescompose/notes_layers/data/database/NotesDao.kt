package com.example.notescompose.notes_layers.data.database

import androidx.room.*
import com.example.notescompose.notes_layers.domain.models.NoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteItem>>


    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteItem: NoteItem)

    @Delete
    suspend fun deleteNote(noteItem: NoteItem)

}