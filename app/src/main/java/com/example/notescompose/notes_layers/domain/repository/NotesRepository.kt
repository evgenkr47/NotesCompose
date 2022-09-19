package com.example.notescompose.notes_layers.domain.repository

import com.example.notescompose.notes_layers.domain.models.NoteItem
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotesList(): Flow<List<NoteItem>>

    suspend fun getNoteById(id: Int): NoteItem?

    suspend fun insertNote(noteItem: NoteItem)

    suspend fun deleteNote(noteItem: NoteItem)
}