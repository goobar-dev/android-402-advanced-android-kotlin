package dev.goobar.androidstudyguide.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.goobar.androidstudyguide.BuildConfig
import dev.goobar.androidstudyguide.db.AppDatabase
import dev.goobar.androidstudyguide.db.NoteDao
import dev.goobar.androidstudyguide.db.RepoDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    fun provideRepoDao(appDatabase: AppDatabase): RepoDao {
        return appDatabase.repoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val supportFactory =
            SupportFactory(SQLiteDatabase.getBytes(BuildConfig.DATABASE_PASSWORD.toCharArray()))
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "android-study-guide-database"
        ).openHelperFactory(supportFactory).build()
    }
}