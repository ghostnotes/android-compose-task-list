package co.ghostnotes.sample.compose.tasklist.extensions

import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity
import co.ghostnotes.sample.compose.tasklist.domain.model.Task

fun TaskEntity.toModel(): Task {
    return Task(
        title = title,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}