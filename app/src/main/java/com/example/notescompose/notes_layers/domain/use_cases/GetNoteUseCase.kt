package com.example.notescompose.notes_layers.domain.use_cases

import com.example.notescompose.notes_layers.domain.models.NoteItem
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import javax.inject.Inject

/**
 * Here we get our note by id
 */

class GetNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int): NoteItem?{
       return repository.getNoteById(id)
    }
}