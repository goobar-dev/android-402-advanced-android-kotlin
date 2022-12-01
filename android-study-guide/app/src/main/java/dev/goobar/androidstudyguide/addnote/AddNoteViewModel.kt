package dev.goobar.androidstudyguide.addnote

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.androidstudyguide.db.NoteDao
import dev.goobar.androidstudyguide.notes.NoteViewItem
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {

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
    fun onSaveClicked() { /* TODO */ }
    fun onCategoryClicked(newCategory: String) { selectedCategory = newCategory }
}