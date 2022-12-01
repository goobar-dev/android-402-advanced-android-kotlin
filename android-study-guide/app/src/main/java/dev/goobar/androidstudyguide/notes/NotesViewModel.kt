package dev.goobar.androidstudyguide.notes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.androidstudyguide.db.NoteDao
import dev.goobar.data.NoteEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@Immutable
data class NoteViewItem(
    val id: Int,
    val title: String,
    val category: String,
    val content: String,
)
private fun NoteEntity.toViewItem() = NoteViewItem(id, title, category, content)

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {

    val notes = noteDao
        .getAll()
        .map { notes -> notes.map { note -> note.toViewItem() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList<NoteViewItem>())

    fun onNoteClicked(note: NoteViewItem) {
        // TODO
    }
}