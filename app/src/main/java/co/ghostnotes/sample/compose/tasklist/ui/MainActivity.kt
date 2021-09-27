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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.ghostnotes.sample.compose.tasklist.R
import co.ghostnotes.sample.compose.tasklist.di.MainDispatcher
import co.ghostnotes.sample.compose.tasklist.ui.list.TaskList
import co.ghostnotes.sample.compose.tasklist.ui.list.TaskListViewModel
import co.ghostnotes.sample.compose.tasklist.ui.task.Task
import co.ghostnotes.sample.compose.tasklist.ui.task.TaskViewModel
import co.ghostnotes.sample.compose.tasklist.ui.theme.TaskListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject @MainDispatcher lateinit var mainDispatcher: CoroutineDispatcher

    private val mainViewModel: MainViewModel by viewModels()
    private val taskListViewModel: TaskListViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //taskListViewModel.insertDummyTasks()

        setContent {
            Main(
                mainDispatcher = mainDispatcher,
                mainViewModel = mainViewModel,
                taskListViewModel = taskListViewModel,
                taskViewModel = taskViewModel,
            )
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun Main(
    mainDispatcher: CoroutineDispatcher,
    mainViewModel: MainViewModel,
    taskListViewModel: TaskListViewModel,
    taskViewModel: TaskViewModel,
) {
    val fabVisible by mainViewModel.fabVisible.collectAsState()
    val navController = rememberNavController()

    TaskListTheme {
        Scaffold(
            topBar = { AppBar(mainViewModel = mainViewModel) },
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                if (fabVisible) {
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
                }
            },
        ) {
            MainContent(
                mainDispatcher = mainDispatcher,
                navController = navController,
                mainViewModel = mainViewModel,
                taskListViewModel = taskListViewModel,
                taskViewModel = taskViewModel,
            )
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun MainContent(
    mainDispatcher: CoroutineDispatcher,
    navController: NavHostController,
    mainViewModel: MainViewModel,
    taskListViewModel: TaskListViewModel,
    taskViewModel: TaskViewModel,
) {
    fun setAppBarTitle(title: String) {
        mainViewModel.setAppBarTitle(title)
    }
    fun setFabVisible(visible: Boolean) {
        mainViewModel.setFabVisible(visible)
    }

    val coroutineScope = rememberCoroutineScope()

    NavHost(navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) {
            Timber.d("### navigate to Task list.")
            setAppBarTitle(stringResource(id = R.string.screen_title_task_list))
            setFabVisible(true)

            TaskList(taskListViewModel)
        }
        composable(Screen.Task.route) {
            Timber.d("### navigate to Task.")
            setAppBarTitle(stringResource(id = R.string.screen_title_task))
            setFabVisible(false)

            Task(taskViewModel = taskViewModel) {
                Timber.d("### onSuccess()")
                coroutineScope.launch(mainDispatcher) {
                    navController.popBackStack(
                        route = Screen.TaskList.route,
                        inclusive = false,
                        saveState = false
                    )
                }
            }
        }
    }
}

@Composable
fun AppBar(mainViewModel: MainViewModel) {
    val appBarTitle: String by mainViewModel.appBarTitle.collectAsState()

    TopAppBar(
        title = { Text(text = appBarTitle) }
    )
}
