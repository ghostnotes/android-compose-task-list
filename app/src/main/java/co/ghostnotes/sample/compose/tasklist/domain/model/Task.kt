package co.ghostnotes.sample.compose.tasklist.domain.model

import java.util.Date

data class Task(
    val title: String,
    val description: String?,
    val createdAt: Date,
    val updatedAt: Date,
)
