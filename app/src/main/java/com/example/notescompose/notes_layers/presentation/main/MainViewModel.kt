package com.example.notescompose.notes_layers.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescompose.notes_layers.domain.models.NoteItem
import com.example.notescompose.notes_layers.domain.use_cases.AddNoteUseCase
import com.example.notescompose.notes_layers.domain.use_cases.DeleteNoteUseCase
import com.example.notescompose.notes_layers.domain.use_cases.GetNotesListUseCase
import com.example.notescompose.notes_layers.domain.utils.NoteOrder
import com.example.notescompose.notes_layers.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNotesListUseCase: GetNotesListUseCase
): ViewModel() {

    private val _state = mutableStateOf(NotesStates())
    var state: State<NotesStates> = _state

    private var recentlyDeleteNote: NoteItem? = null

    private var getNotesJob: Job? = null


    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class
                    && state.value.noteOrder.orderType == event.noteOrder.orderType
                ){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.noteItem)
                    recentlyDeleteNote = event.noteItem
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    addNoteUseCase(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = getNotesListUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
        }.launchIn(viewModelScope)
    }

}