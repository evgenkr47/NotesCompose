package com.example.notescompose.notes_layers.domain.use_cases

import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import com.example.notescompose.notes_layers.domain.utils.NoteOrder
import com.example.notescompose.notes_layers.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Here we get our notes list, where default sorted by date
 */

class GetNotesListUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotesList().map { notes ->
            when(noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.add_time }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.add_time }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }

        }
    }
}