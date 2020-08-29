package com.android.simplenotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.simplenotes.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM note_table ORDER BY createdAt DESC")
    fun getListNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Notes)

    @Query("DELETE FROM note_table WHERE id =:id")
    suspend fun deleteNotes(id: Int)

    @Update
    suspend fun update(notes: Notes)

}