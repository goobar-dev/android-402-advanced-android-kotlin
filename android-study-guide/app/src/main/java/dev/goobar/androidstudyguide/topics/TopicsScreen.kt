package dev.goobar.androidstudyguide.topics

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TopicsScreen(viewModel: TopicsViewModel = viewModel()) {

  val topics by viewModel.topics.collectAsState()
  val scaffoldState = rememberScaffoldState()

  LaunchedEffect(viewModel) {
    viewModel.events.collect { event ->
      when (event) {
        is TopicsViewModel.Events.ShowTopicClickedMessage -> {
          scaffoldState.snackbarHostState.showSnackbar(event.message)
        }
      }
    }
  }

  Scaffold(
    scaffoldState = scaffoldState
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(1f),
      contentPadding = PaddingValues(20.dp),
      verticalArrangement = spacedBy(16.dp)
    ) {
      items(topics) { topic ->
        TopicCard(topic, viewModel::onTopicClicked)
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopicCard(topic: TopicViewItem, onClick: (TopicViewItem) -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth(1f),
    elevation = 4.dp,
    onClick = { onClick(topic) }
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Text(text = topic.title, style = MaterialTheme.typography.h3)
      Text(text = topic.categories.joinToString(", "), style = MaterialTheme.typography.subtitle2)
      Spacer(modifier = Modifier.size(8.dp))
      Text(text = topic.content, style = MaterialTheme.typography.subtitle1)
    }
  }
}