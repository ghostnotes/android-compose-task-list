package co.ghostnotes.sample.compose.tasklist.domain.usecase

import androidx.paging.PagingSource
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity

//interface FindTasksUseCase : UseCase<Unit, List<Task>>
//interface FindTasksUseCase : UseCase<Unit, PagingSource<Int, Task>>
interface FindTasksUseCase : UseCase<Unit, PagingSource<Int, TaskEntity>>
