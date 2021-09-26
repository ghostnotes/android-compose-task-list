package co.ghostnotes.sample.compose.tasklist.data.usecase

import androidx.paging.PagingSource
import co.ghostnotes.sample.compose.tasklist.data.dao.TaskDao
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.domain.usecase.FindTasksUseCase
import javax.inject.Inject

class FindTasksUseCaseImpl @Inject constructor(
    private val taskDao: TaskDao
) : FindTasksUseCase {
    override fun invoke(input: Unit): PagingSource<Int, TaskEntity> {
        return taskDao.findAll()
    }
}