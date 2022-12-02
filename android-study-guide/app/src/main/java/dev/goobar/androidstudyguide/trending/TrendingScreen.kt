package dev.goobar.androidstudyguide.trending

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.goobar.androidstudyguide.R
import dev.goobar.androidstudyguide.design.LoadingCard
import dev.goobar.androidstudyguide.design.StudyGuideAppBar
import dev.goobar.androidstudyguide.notes.NoteCard
import dev.goobar.androidstudyguide.notes.NoteViewItem

@Composable
fun TrendingScreen(onBackClick: () -> Unit, viewModel: TrendingViewModel = hiltViewModel()) {

    val repos by viewModel.repos.collectAsState()

    Scaffold(
        topBar = { StudyGuideAppBar(stringResource(R.string.trending), onBackClick) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(1f),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
        ) {
            if (repos.isEmpty()) {
                items(10) { LoadingCard() }
            } else {
                items(repos, key = { it.name }) { repo ->
                    RepoCard(repo)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepoCard(repo: FeaturedRepoViewItem) {
    Card(
        modifier = Modifier.fillMaxWidth(1f),
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = repo.displayName, style = MaterialTheme.typography.h3)
            Text(text = "@${repo.createdBy}", style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = repo.description, style = MaterialTheme.typography.subtitle1)
        }
    }
}