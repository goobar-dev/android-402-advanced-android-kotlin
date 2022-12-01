package dev.goobar.androidstudyguide.design

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .placeholder(true, highlight = PlaceholderHighlight.fade()),
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "placeholder", style = MaterialTheme.typography.h3)
            Text(text = "placeholder", style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "placeholder", style = MaterialTheme.typography.subtitle1)
        }
    }
}