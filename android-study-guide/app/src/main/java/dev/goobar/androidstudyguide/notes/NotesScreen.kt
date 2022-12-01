package dev.goobar.androidstudyguide.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.design.LoadingCard
import dev.goobar.androidstudyguide.design.StudyGuideAppBar
import dev.goobar.androidstudyguide.topics.TopicViewItem

@Composable
fun NotesScreen(
    onBackClick: () -> Unit,
    onAddNoteClick: () -> Unit,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val notes by viewModel.notes.collectAsState()

    Scaffold(
        topBar = { StudyGuideAppBar(stringResource(R.string.notes), onBackClick) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNoteClick) {
                Icon(painter = painterResource(R.drawable.ic_create), contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(1f),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
        ) {
            if (notes.isEmpty()) {
                items(10) { LoadingCard() }
            } else {
                items(notes, key = { it.id }) { note ->
                    NoteCard(note = note, viewModel::onNoteClicked)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(note: NoteViewItem, onClick: (NoteViewItem) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(1f),
        elevation = 4.dp,
        onClick = { onClick(note) }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.h3)
            Text(text = note.category, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.subtitle1)
        }
    }
}