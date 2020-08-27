package com.android.simplenotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.simplenotes.dao.NotesDao
import com.android.simplenotes.model.Notes

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
public abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao(): NotesDao

    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context):  NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}