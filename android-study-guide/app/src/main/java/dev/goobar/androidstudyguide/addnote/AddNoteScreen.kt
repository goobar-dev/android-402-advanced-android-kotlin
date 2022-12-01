package dev.goobar.androidstudyguide.addnote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.design.LoadingCard
import dev.goobar.androidstudyguide.design.StudyGuideAppBar
import dev.goobar.androidstudyguide.notes.NoteCard
import dev.goobar.androidstudyguide.notes.NotesViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AddNoteScreen(
    onBackClick: () -> Unit,
    viewModel: AddNoteViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.events.collect {
            when (it) {
                AddNoteViewModel.Event.SaveCompleted -> onBackClick()
            }
        }
    }

    val showSaveButton by viewModel.showSaveButton.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Scaffold(
        topBar = { StudyGuideAppBar(stringResource(R.string.add_note), onBackClick) },
        floatingActionButton = {
            AnimatedVisibility(visible = showSaveButton) {
                FloatingActionButton(onClick = viewModel::onSaveClicked) {
                    Icon(painter = painterResource(R.drawable.ic_save), contentDescription = "Save Note")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                value = viewModel.title,
                onValueChange = viewModel::onTitleChanged,
                label = { Text(stringResource(R.string.note_title_label))}
            )

            CategoryDropdown(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                selectedCategory,
                viewModel.categories.toImmutableList(),
                viewModel::onCategoryClicked
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                value = viewModel.content,
                onValueChange = viewModel::onContentChanged,
                singleLine = false,
                label = { Text(stringResource(R.string.note_content_label))}
            )
        }
    }
}

@Composable
private fun CategoryDropdown(
    modifier: Modifier = Modifier,
    selected: String,
    categories: ImmutableList<String>,
    onCategoryClicked: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier) {

        val source = remember { MutableInteractionSource() }
        val isPressed by source.collectIsPressedAsState()
        if (isPressed) expanded = true

        OutlinedTextField(
            interactionSource = source,
            modifier = Modifier
                .fillMaxWidth(1f)
                .clickable(onClick = { expanded = true }),
            readOnly = true,
            value = selected,
            onValueChange = {},
            label = { Text(stringResource(R.string.note_category_label)) }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(onClick = {
                    onCategoryClicked(category)
                    expanded = false
                }) {
                    Text(category)
                }
            }
        }
    }
}