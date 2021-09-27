package co.ghostnotes.sample.compose.tasklist.domain.usecase

import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity

interface InsertTaskUseCase : UseCase<TaskEntity, Result<TaskEntity>>
