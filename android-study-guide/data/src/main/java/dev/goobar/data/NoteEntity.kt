package dev.goobar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    val title: String,
    val category: String,
    val content: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}