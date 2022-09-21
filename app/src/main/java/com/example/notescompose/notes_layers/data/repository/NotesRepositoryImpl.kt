package com.example.notescompose.notes_layers.data.repository

import com.example.notescompose.notes_layers.data.database.NotesDao
import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override fun getNotesList(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notesDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }
}