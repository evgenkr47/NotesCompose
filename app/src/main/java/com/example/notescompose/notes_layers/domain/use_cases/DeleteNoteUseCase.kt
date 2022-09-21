package com.example.notescompose.notes_layers.domain.use_cases

import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import javax.inject.Inject

/**
 * Here we delete our note
 */

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}