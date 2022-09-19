package com.example.notescompose.notes_layers.domain.use_cases

import com.example.notescompose.notes_layers.domain.models.InvalidNoteException
import com.example.notescompose.notes_layers.domain.models.NoteItem
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import javax.inject.Inject

/**
 * Here we add our note and check for fill exceptions (if the element or content is empty).
 */

class AddNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(noteItem: NoteItem){
        if (noteItem.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (noteItem.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty")
        }
        else {
            repository.insertNote(noteItem)
        }
    }
}