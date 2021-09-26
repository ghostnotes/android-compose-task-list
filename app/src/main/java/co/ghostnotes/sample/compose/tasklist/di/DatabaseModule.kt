package co.ghostnotes.sample.compose.tasklist.di

import android.content.Context
import androidx.room.Room
import co.ghostnotes.sample.compose.tasklist.data.TaskListDatabase
import co.ghostnotes.sample.compose.tasklist.data.TaskListDatabase.Companion.MIGRATION_1_2
import co.ghostnotes.sample.compose.tasklist.data.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): TaskListDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskListDatabase::class.java, "task-list-database"
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: TaskListDatabase): TaskDao = database.taskDao()
}