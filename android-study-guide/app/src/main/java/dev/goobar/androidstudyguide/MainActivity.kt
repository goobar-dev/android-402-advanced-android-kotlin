package dev.goobar.androidstudyguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.goobar.androidstudyguide.addnote.AddNoteScreen
import dev.goobar.androidstudyguide.home.HomeScreen
import dev.goobar.androidstudyguide.notes.NotesScreen
import dev.goobar.androidstudyguide.theme.AndroidStudyGuideTheme
import dev.goobar.androidstudyguide.topics.TopicsScreen
import dev.goobar.androidstudyguide.trending.TrendingScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AndroidStudyGuideTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

          val navController = rememberNavController()
          val backClickHandler: () -> Unit = remember {
            { navController.popBackStack() }
          }

          val addNoteClickHandler: () -> Unit = remember {
            { navController.navigate(NavigationDestinations.ADD_NOTE)}
          }

          // Defines the navigation graph for the application
          NavHost(navController, startDestination = NavigationDestinations.HOME) {
            composable(NavigationDestinations.HOME) { HomeScreen(navController) }
            composable(NavigationDestinations.TOPICS) { TopicsScreen(backClickHandler) }
            composable(NavigationDestinations.NOTES) { NotesScreen(backClickHandler, addNoteClickHandler) }
            composable(NavigationDestinations.ADD_NOTE) { AddNoteScreen(backClickHandler)}
            composable(NavigationDestinations.TRENDING) { TrendingScreen(backClickHandler) }
          }
        }
      }
    }
  }
}