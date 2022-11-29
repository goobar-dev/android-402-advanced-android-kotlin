package dev.goobar.composescorekeeper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val MAX_SCORE = 20
private fun Int.isMaxScore() = this >= MAX_SCORE

@Composable
fun MainScreen() {

  var currentScore by remember { mutableStateOf(0) }

  Column(
    modifier = Modifier.fillMaxSize().padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Score", style = MaterialTheme.typography.h1)
    Text(
      text = "$currentScore",
      style = MaterialTheme.typography.h1,
      color = if (currentScore.isMaxScore()) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground
    )

    if (currentScore.isMaxScore()) {
      Text(text = "You Won!", style = MaterialTheme.typography.subtitle1)
    }

    Spacer(modifier = Modifier.weight(1f)) // ensure content fills screen

    if (currentScore.isMaxScore()) {
      ResetButton { currentScore = 0 }
    } else {
      PlusOneButton { currentScore++ }
    }
  }
}

@Composable
private fun PlusOneButton(onClick: () -> Unit) {
  Button(onClick = onClick) {
    Text(text = "+1", style = MaterialTheme.typography.subtitle1)
  }
}

@Composable
private fun ResetButton(onClick: () -> Unit) {
  Button(onClick = onClick) {
    Text(text = "Reset", style = MaterialTheme.typography.subtitle1)
  }
}