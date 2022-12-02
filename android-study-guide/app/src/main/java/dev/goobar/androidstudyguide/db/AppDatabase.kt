package dev.goobar.androidstudyguide.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.goobar.data.NoteEntity
import dev.goobar.data.RepoEntity

@Database(
    version = 1,
    entities = [NoteEntity::class, RepoEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun repoDao(): RepoDao
}