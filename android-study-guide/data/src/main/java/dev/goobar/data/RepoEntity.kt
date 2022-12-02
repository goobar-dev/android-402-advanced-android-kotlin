package dev.goobar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoEntity(
  @PrimaryKey
  val name: String,
  val displayName: String,
  val description: String,
  val createdBy: String,
)