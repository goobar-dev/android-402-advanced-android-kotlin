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
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.goobar.analytics.LogcatLogger
import dev.goobar.analytics.Logger

@Composable
fun TopicsScreen() {

  val topics = remember { dev.goobar.data.SAMPLE_TOPICS }
  val logger: dev.goobar.analytics.Logger = remember { dev.goobar.analytics.LogcatLogger() }
  val context = LocalContext.current

  Scaffold() { paddingValues ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(1f),
      contentPadding = PaddingValues(20.dp),
      verticalArrangement = spacedBy(16.dp)
    ) {
      items(topics) { topic ->
        TopicCard(topic) {
          logger.log("Topic Clicked", mapOf("topic_title" to it.title))
          Toast.makeText(context, "Clicked ${it.title}", Toast.LENGTH_SHORT).show()
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopicCard(topic: dev.goobar.data.Topic, onClick: (dev.goobar.data.Topic) -> Unit) {
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