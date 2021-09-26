package co.ghostnotes.sample.compose.tasklist.ui.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.ghostnotes.sample.compose.tasklist.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Task(taskViewModel: TaskViewModel) {
    val title: String by taskViewModel.title.collectAsState()
    val description: String by taskViewModel.description.collectAsState()
    val addTaskButtonEnabled by taskViewModel.addTaskButtonEnabled.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.hint_title)) },
            value = title,
            onValueChange = { taskViewModel.setTitle(it) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            label = { Text(text = stringResource(id = R.string.hint_description)) },
            value = description,
            onValueChange = { taskViewModel.setDescription(it) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* TODO add task */ },
            enabled = addTaskButtonEnabled
        ) {
            Text(
                text = stringResource(id = R.string.button_add_task),
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}