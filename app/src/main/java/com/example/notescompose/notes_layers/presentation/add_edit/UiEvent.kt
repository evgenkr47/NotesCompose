package com.example.notescompose.notes_layers.presentation.add_edit

sealed class UiEvent{
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}
