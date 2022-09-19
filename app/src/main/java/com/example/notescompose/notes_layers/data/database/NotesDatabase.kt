package com.example.notescompose.notes_layers.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notescompose.notes_layers.domain.models.NoteItem


@Database(
    entities = [NoteItem::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase()  {
    abstract fun getNotesDao(): NotesDao

    companion object{
        const val DATABASE_NAME = "notes_database"
    }
}