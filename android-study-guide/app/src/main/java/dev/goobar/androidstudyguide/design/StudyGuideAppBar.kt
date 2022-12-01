package dev.goobar.androidstudyguide.design

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.goobar.androidstudyguide.R

@Composable
fun StudyGuideAppBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(modifier = Modifier.height(80.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "Back")
            }
            Text(text = title, style = MaterialTheme.typography.h5)
        }
    }
}