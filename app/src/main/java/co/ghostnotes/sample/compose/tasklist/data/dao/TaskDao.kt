package co.ghostnotes.sample.compose.tasklist.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY updated_at DESC")
    fun findAll(): PagingSource<Int, TaskEntity>

    @Insert
    fun insertAll(vararg tasks: TaskEntity)

    @Query("DELETE FROM task")
    fun deleteAll()
}