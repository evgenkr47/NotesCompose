package com.example.notescompose.notes_layers.domain.utils

/**
 * This class was created for sorted ours notes by ascending or descending
 */
sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}