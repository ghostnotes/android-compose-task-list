package co.ghostnotes.sample.compose.tasklist.data.usecase

import co.ghostnotes.sample.compose.tasklist.data.dao.TaskDao
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.domain.usecase.InsertTaskUseCase
import timber.log.Timber
import javax.inject.Inject

class InsertTaskUseCaseImpl @Inject constructor(
    private val taskDao: TaskDao
) : InsertTaskUseCase {
    override fun invoke(input: TaskEntity): Result<TaskEntity> {
        return try {
            taskDao.insertAll(input)
            Result.success(input)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}
