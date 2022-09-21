package com.example.notescompose.notes_layers.presentation.util

sealed class Screen(val route: String){
    object MainScreen: Screen(MAIN_SCREEN_ROUTE)
    object AddEditScreen: Screen(ADD_EDIT_SCREEN_ROUTE)

    companion object{
        private const val MAIN_SCREEN_ROUTE = "main_screen"
        private const val ADD_EDIT_SCREEN_ROUTE = "add_edit_screen"
    }
}