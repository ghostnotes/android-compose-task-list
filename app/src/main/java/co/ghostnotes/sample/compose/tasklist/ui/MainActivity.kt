package co.ghostnotes.sample.compose.tasklist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ghostnotes.sample.compose.tasklist.R
import co.ghostnotes.sample.compose.tasklist.ui.list.TaskList
import co.ghostnotes.sample.compose.tasklist.ui.list.TaskListViewModel
import co.ghostnotes.sample.compose.tasklist.ui.task.Task
import co.ghostnotes.sample.compose.tasklist.ui.task.TaskViewModel
import co.ghostnotes.sample.compose.tasklist.ui.theme.TaskListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskListViewModel: TaskListViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //taskListViewModel.insertDummyTasks()

        setContent {
            Main(
                taskListViewModel = taskListViewModel,
                taskViewModel = taskViewModel,
            )
        }
    }
}

@Composable
fun Main(
    taskListViewModel: TaskListViewModel,
    taskViewModel: TaskViewModel,
) {
    val navController = rememberNavController()

    TaskListTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { AppBar(title = stringResource(id = R.string.screen_title_task_list)) },
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.Task.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.content_description_add_task)
                    )
                }
            },
        ) {
            MainContent(
                navController = navController,
                taskListViewModel = taskListViewModel,
                taskViewModel = taskViewModel,
            )
        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController,
    taskListViewModel: TaskListViewModel,
    taskViewModel: TaskViewModel,
) {
    NavHost(navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) { TaskList(taskListViewModel) }
        composable(Screen.Task.route) { Task(taskViewModel) }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}
