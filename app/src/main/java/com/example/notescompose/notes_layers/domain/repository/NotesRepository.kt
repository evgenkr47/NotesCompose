package com.example.notescompose.notes_layers.domain.repository

import com.example.notescompose.notes_layers.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotesList(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}