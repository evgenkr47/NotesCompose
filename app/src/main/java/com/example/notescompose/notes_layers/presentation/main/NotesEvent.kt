package com.example.notescompose.notes_layers.presentation.main

import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.utils.NoteOrder

/**
 * Our events(what we can do) on MainScreen
 * Our Order(from domain.utils.NoteOrder)
 * Delete note event by click on delete item
 * Restore event, where we can restore our note after click on delete item
 * Toggle order section event(section, where we can sorted our notes)
 */

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()


}