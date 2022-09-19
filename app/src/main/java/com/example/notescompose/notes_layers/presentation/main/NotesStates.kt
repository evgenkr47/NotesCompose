package com.example.notescompose.notes_layers.presentation.main

import com.example.notescompose.notes_layers.domain.models.NoteItem
import com.example.notescompose.notes_layers.domain.utils.NoteOrder
import com.example.notescompose.notes_layers.domain.utils.OrderType

/**
 * This is where we store the states(our states on Main Screen)
 * Default states(notes list is empty, sorted by data(descending), sorting order section - invisible)
 */

data class NotesStates(
    val notes: List<NoteItem> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
