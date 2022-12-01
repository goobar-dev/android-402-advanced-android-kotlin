package dev.goobar.androidstudyguide.trending

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.design.StudyGuideAppBar

@Composable
fun TrendingScreen(onBackClick: () -> Unit, viewModel: TrendingViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { StudyGuideAppBar(stringResource(R.string.trending), onBackClick) }
    ) { paddingValues ->

    }
}