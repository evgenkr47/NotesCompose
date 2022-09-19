package com.example.notescompose.notes_layers.domain.utils


/**
 * This class was created for sorted ours notes by title, date, color
 * and ascending or descending
 */

sealed class NoteOrder(val orderType: OrderType){
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }


}


