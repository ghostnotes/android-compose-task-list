package co.ghostnotes.sample.compose.tasklist.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingListItem(modifier: Modifier) {
    Box(modifier = modifier.padding(16.dp)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
