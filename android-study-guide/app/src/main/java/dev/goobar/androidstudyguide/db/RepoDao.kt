package dev.goobar.androidstudyguide.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.goobar.data.NoteEntity
import dev.goobar.data.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

  @Query("SELECT * FROM repoentity")
  fun getAll(): Flow<List<RepoEntity>>

  @Insert
  suspend fun save(repos: List<RepoEntity>)

}