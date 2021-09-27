package co.ghostnotes.sample.compose.tasklist.ui.list

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import co.ghostnotes.sample.compose.tasklist.R
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.extensions.format
import co.ghostnotes.sample.compose.tasklist.ui.view.LoadingListItem
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun TaskList(viewModel: TaskListViewModel) {
    val taskItems: LazyPagingItems<TaskEntity> = viewModel.taskList.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(taskItems) { task ->
            TaskListItem(task = task)
        }

        taskItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingListItem(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingListItem(modifier = Modifier.fillParentMaxWidth()) }
                }
                loadState.refresh is LoadState.NotLoading -> {
                }
                loadState.append is LoadState.NotLoading -> {
                }
            }
        }
    }
}

@Composable
fun TaskListItem(modifier: Modifier = Modifier, task: TaskEntity?) {
    Column(
        modifier = modifier
            .clickable(onClick = { /**/ })
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = task?.title ?: stringResource(id = R.string.label_no_title),
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = task?.description ?: stringResource(id = R.string.label_no_description),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = task?.createdAt?.format() ?: stringResource(id = R.string.label_no_date),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.End
        )
    }
}
