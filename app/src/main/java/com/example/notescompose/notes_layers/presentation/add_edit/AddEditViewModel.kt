package com.example.notescompose.notes_layers.presentation.add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescompose.notes_layers.domain.models.InvalidNoteException
import com.example.notescompose.notes_layers.domain.models.Note
import com.example.notescompose.notes_layers.domain.use_cases.AddNoteUseCase
import com.example.notescompose.notes_layers.domain.use_cases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _titleState = mutableStateOf(NoteTextState(
        hint = "Заголовок"
    ))
    val titleState: State<NoteTextState> = _titleState

    private val _contentState = mutableStateOf(NoteTextState(
        hint = "Описание"
    ))
    val contentState: State<NoteTextState> = _contentState

    private val _colorState = mutableStateOf(
        Note.noteColors.random().toArgb())
    val colorState: State<Int> = _colorState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentId: Int ?= null

    init {

        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    getNoteUseCase(noteId)?.also { note ->
                        currentId = note.id
                        _titleState.value = titleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _contentState.value = contentState.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _colorState.value = note.color
                    }
                }
            }

        }

    }


    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle -> {
                _titleState.value = titleState.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && titleState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _contentState.value = contentState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _contentState.value = contentState.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && contentState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _colorState.value = event.color
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        addNoteUseCase(
                            Note(
                                title = titleState.value.text,
                                content = contentState.value.text,
                                add_time = System.currentTimeMillis(),
                                color = colorState.value,
                                id = currentId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    }
                    catch (e: InvalidNoteException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }


}