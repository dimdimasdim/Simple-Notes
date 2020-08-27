package com.android.simplenotes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.simplenotes.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * from notes_table ORDER BY created ASC")
    fun getListNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Notes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}