package com.example.notescompose.notes_layers.data.repository

import com.example.notescompose.notes_layers.data.database.NotesDao
import com.example.notescompose.notes_layers.domain.models.NoteItem
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override fun getNotesList(): Flow<List<NoteItem>> {
        return notesDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): NoteItem? {
        return notesDao.getNoteById(id)
    }

    override suspend fun insertNote(noteItem: NoteItem) {
        notesDao.insertNote(noteItem)
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        notesDao.deleteNote(noteItem)
    }
}