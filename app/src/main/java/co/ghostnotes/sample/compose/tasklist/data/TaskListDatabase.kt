package co.ghostnotes.sample.compose.tasklist.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import co.ghostnotes.sample.compose.tasklist.data.dao.TaskDao
import co.ghostnotes.sample.compose.tasklist.data.entity.TaskEntity

@Database(
    version = 2,
    entities = [TaskEntity::class],
)
@TypeConverters(Converters::class)
abstract class TaskListDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Task ADD COLUMN updated_at INTEGER DEFAULT 0 NOT NULL")
            }

        }
    }
}