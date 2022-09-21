package com.example.notescompose.notes_layers.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notescompose.ui.theme.*

@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val content: String,
    val add_time: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
)
{
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
