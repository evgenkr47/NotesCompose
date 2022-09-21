package com.example.notescompose.notes_layers.presentation.add_edit

data class NoteTextState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)
