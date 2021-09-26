package co.ghostnotes.sample.compose.tasklist.ui

sealed class Screen(val route: String) {
    object TaskList : Screen("taskList")
    object Task : Screen("task")
}
