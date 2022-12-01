package dev.goobar.androidstudyguide.topics

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.design.LoadingCard
import dev.goobar.androidstudyguide.design.StudyGuideAppBar

@Composable
fun TopicsScreen(
  onBackClick: () -> Unit,
  viewModel: TopicsViewModel = hiltViewModel()
) {

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
    scaffoldState = scaffoldState,
    topBar = {
      StudyGuideAppBar(stringResource(R.string.topics), onBackClick)
    }
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(1f),
      contentPadding = PaddingValues(20.dp),
      verticalArrangement = spacedBy(16.dp)
    ) {
      if (topics.isEmpty()) {
        items(10) {
          LoadingCard()
        }
      } else {
        items(topics) { topic ->
          TopicCard(topic, viewModel::onTopicClicked)
        }
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