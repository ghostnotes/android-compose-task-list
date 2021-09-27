package co.ghostnotes.sample.compose.tasklist.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.domain.usecase.InsertTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import java.util.Date
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TaskViewModel @Inject constructor(
    //@IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val insertTaskUseCase: InsertTaskUseCase,
): ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title
    fun setTitle(title: String) {
        _title.value = title
    }

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description
    fun setDescription(description: String) {
        _description.value = description
    }

    val addTaskButtonEnabled: StateFlow<Boolean> = _title.mapLatest { title -> title.isNotBlank() }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    //private val _navigateToTaskList = Channel<Unit>()
    //val navigateToTaskList = _navigateToTaskList.receiveAsFlow()

    fun insertTask(): Result<TaskEntity> {
        /*
        viewModelScope.launch(ioDispatcher) {
            val result = insertTaskUseCase(createTask())

            when {
                result.isSuccess -> _navigateToTaskList.send(Unit)
                result.isFailure -> Timber.e(result.exceptionOrNull())
            }
        }
         */

        return insertTaskUseCase(createTask())
    }

    private fun createTask(): TaskEntity {
        val now = Date()

        return TaskEntity(
            title = _title.value,
            description = _description.value,
            createdAt = now,
            updatedAt = now
        )
    }
}
