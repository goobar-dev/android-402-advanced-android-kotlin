package dev.goobar.androidstudyguide.addnote

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.androidstudyguide.db.NoteDao
import dev.goobar.androidstudyguide.notes.NoteViewItem
import dev.goobar.data.NoteEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {

    sealed class Event {
        object SaveCompleted : Event()
    }

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    var title by mutableStateOf("")
        private set
    var content by mutableStateOf("")
        private set
    val categories by mutableStateOf(listOf("Android", "Kotlin", "Software Development", "Cloud"))
    var selectedCategory by mutableStateOf("Android")
        private set

    val showSaveButton: StateFlow<Boolean> =
        snapshotFlow { title }.combine(snapshotFlow { content }) { title, content ->
            title.isNotBlank() && content.isNotBlank()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    fun onTitleChanged(newTitle: String) { title = newTitle }
    fun onContentChanged(newContent: String) { content = newContent }
    fun onSaveClicked() {
        viewModelScope.launch {
            noteDao.save(NoteEntity(title, selectedCategory, content))
            _events.emit(Event.SaveCompleted)
        }
    }
    fun onCategoryClicked(newCategory: String) { selectedCategory = newCategory }
}