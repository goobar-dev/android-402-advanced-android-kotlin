package dev.goobar.androidstudyguide.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.goobar.androidstudyguide.NavigationDestinations
import dev.goobar.androidstudyguide.R

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(20.dp)) {
        DestinationCard(title = stringResource(R.string.topics), modifier = Modifier.weight(1f)) {
            navController.navigate(NavigationDestinations.TOPICS)
        }

        DestinationCard(title = stringResource(R.string.notes), modifier = Modifier.weight(1f)) {
            navController.navigate(NavigationDestinations.NOTES)
        }

        DestinationCard(title = stringResource(R.string.trending), modifier = Modifier.weight(1f)) {
            navController.navigate(NavigationDestinations.TRENDING)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DestinationCard(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.padding(vertical = 8.dp).fillMaxWidth(1f),
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = title, style = MaterialTheme.typography.h3)
        }
    }
}