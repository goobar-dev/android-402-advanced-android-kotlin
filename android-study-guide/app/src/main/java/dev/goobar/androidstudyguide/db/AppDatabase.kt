package dev.goobar.androidstudyguide.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.goobar.data.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}