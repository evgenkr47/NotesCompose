package com.example.notescompose.notes_layers.domain.use_cases

import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import javax.inject.Inject

/**
 * Here we get our note by id
 */

class GetNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int): Note?{
       return repository.getNoteById(id)
    }
}