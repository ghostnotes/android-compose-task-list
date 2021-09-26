package co.ghostnotes.sample.compose.tasklist.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import co.ghostnotes.sample.compose.tasklist.data.dao.TaskDao
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.di.IODispatcher
import co.ghostnotes.sample.compose.tasklist.domain.usecase.FindTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val taskDao: TaskDao,
    private val findTasksUseCase: FindTasksUseCase,
) : ViewModel() {

    fun insertDummyTasks() {
        viewModelScope.launch(ioDispatcher) {
            taskDao.deleteAll()

            (1..100).forEach {
                taskDao.insertAll(
                    TaskEntity(
                        title = "title $it",
                        description = "description $it",
                        createdAt = Date(),
                        updatedAt = Date(),
                    )
                )
            }
        }
    }

    val taskList = Pager(PagingConfig(pageSize = 20)) {
        findTasksUseCase(Unit)
    }.flow.cachedIn(viewModelScope)

}