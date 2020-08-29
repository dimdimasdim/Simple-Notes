package com.android.simplenotes.repository

import androidx.lifecycle.LiveData
import com.android.simplenotes.dao.NotesDao
import com.android.simplenotes.model.Notes

class NotesRepository(
    private val notesDao: NotesDao
) {

    val getListNotes: LiveData<List<Notes>> = notesDao.getListNotes()

    suspend fun insertNotes(notes: Notes){
        notesDao.insert(notes)
    }

    suspend fun updateNotes(notes: Notes){
        notesDao.update(notes)
    }

    suspend fun deleteNotes(id: Int){
        notesDao.deleteNotes(id)
    }

}