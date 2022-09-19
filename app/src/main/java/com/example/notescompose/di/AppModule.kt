package com.example.notescompose.di

import android.content.Context
import androidx.room.Room
import com.example.notescompose.notes_layers.data.database.NotesDao
import com.example.notescompose.notes_layers.data.database.NotesDatabase
import com.example.notescompose.notes_layers.data.repository.NotesRepositoryImpl
import com.example.notescompose.notes_layers.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {


    @Binds
    @Singleton
    fun bindRepository(impl: NotesRepositoryImpl):NotesRepository

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        NotesDatabase.DATABASE_NAME
    ).build()

    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao{
        return notesDatabase.getNotesDao()
    }


}