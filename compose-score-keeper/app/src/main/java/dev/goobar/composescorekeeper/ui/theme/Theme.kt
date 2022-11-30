package dev.goobar.composescorekeeper.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = BlueGrey700Dark,
  primaryVariant = Grey900,
  secondary = BlueGrey700Dark,
  onPrimary = Color.White,
  onSecondary = Color.White,
  onBackground = Color.White,
  onSurface = Color.White,
)

private val LightColorPalette = lightColors(
  primary = BlueGrey700,
  primaryVariant = BlueGrey700Dark,
  secondary = Blue700,
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.Black,
  onBackground = Color.Black,
  onSurface = Color.Black,
)

@Composable
fun ComposeScoreKeeperTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}