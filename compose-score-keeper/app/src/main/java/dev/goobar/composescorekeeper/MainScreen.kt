package dev.goobar.composescorekeeper

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.goobar.composescorekeeper.ui.theme.ComposeScoreKeeperTheme

private const val MAX_SCORE = 20
private fun Int.isMaxScore() = this >= MAX_SCORE

@Composable
fun MainScreen(initialScore: Int = 0) {

  val currentScore = rememberSaveable { mutableStateOf(initialScore) }
  val configuration = LocalConfiguration.current

  // wrapping this all in Surface is a shortcut for Theming
  // Column/Row don't trigger any of the "onBackground" or "onSurface" colors by default
  Surface() {
    when (configuration.orientation) {
      Configuration.ORIENTATION_LANDSCAPE -> LandscapeContent(currentScore)
      else -> PortraitContent(score = currentScore)
    }
  }
}

@Composable
private fun LandscapeContent(score: MutableState<Int>) {
  var currentScore by score

  Row(
    modifier = Modifier.fillMaxSize(1f)
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxHeight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = "Score", style = MaterialTheme.typography.h1)
      Text(
        text = "$currentScore",
        style = MaterialTheme.typography.h1,
        color = if (currentScore.isMaxScore()) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground
      )

      AnimatedVisibility(visible = currentScore.isMaxScore()) {
        Text(text = "You Won!", style = MaterialTheme.typography.subtitle1)
      }
    }

    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxHeight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      ActionButton(currentScore.isMaxScore(), { currentScore++ }, { currentScore = 0})
    }
  }
}

@Composable
private fun PortraitContent(score: MutableState<Int>) {
  var currentScore by score

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Score", style = MaterialTheme.typography.h1)
    Text(
      text = "$currentScore",
      style = MaterialTheme.typography.h1,
      color = if (currentScore.isMaxScore()) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground
    )

    AnimatedVisibility(visible = currentScore.isMaxScore()) {
      Text(text = "You Won!", style = MaterialTheme.typography.subtitle1)
    }

    Spacer(modifier = Modifier.weight(1f)) // ensure content fills screen

    ActionButton(currentScore.isMaxScore(), { currentScore++ }, { currentScore = 0})
  }
}

@Composable
private fun ActionButton(isMaxScore: Boolean, onPlusOneClick: () -> Unit, onResetClick: () -> Unit) {
  if (isMaxScore) {
    ResetButton(onResetClick)
  } else {
    PlusOneButton(onPlusOneClick)
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

@Preview(device = Devices.NEXUS_5, group = "phones")
@Preview(device = Devices.NEXUS_5, group = "phones", uiMode = UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_4_XL, group = "phones")
@Preview(device = Devices.NEXUS_10, group = "tablets")
@Preview(device = Devices.NEXUS_7, group = "tablets")
@Preview(fontScale = 1f, group = "font scales")
@Preview(fontScale = 1.5f, group = "font scales")
@Preview(fontScale = 2f, group = "font scales")
@Composable
fun PreviewMainScreen(@PreviewParameter(ScorePreviewParameterProvider::class) score: Int) {
  ComposeScoreKeeperTheme {
    MainScreen(score)
  }
}

class ScorePreviewParameterProvider : PreviewParameterProvider<Int> {
  override val values = sequenceOf(
    0, 5, 20
  )
}