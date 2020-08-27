package com.android.simplenotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.simplenotes.database.NotesDatabase
import com.android.simplenotes.model.Notes
import com.android.simplenotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NotesRepository
    val listAllNotes: LiveData<List<Notes>>

    init {
        val notesDao = NotesDatabase.getDatabase(application).noteDao()
        repository = NotesRepository(notesDao)
        listAllNotes = repository.getListNotes
    }

    fun insertNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insertNotes(notes)
    }
}