package co.ghostnotes.sample.compose.tasklist.di

import co.ghostnotes.sample.compose.tasklist.data.usecase.FindTasksUseCaseImpl
import co.ghostnotes.sample.compose.tasklist.data.usecase.InsertTaskUseCaseImpl
import co.ghostnotes.sample.compose.tasklist.domain.usecase.FindTasksUseCase
import co.ghostnotes.sample.compose.tasklist.domain.usecase.InsertTaskUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindFindTasksUseCase(impl: FindTasksUseCaseImpl): FindTasksUseCase

    @Binds
    abstract fun bindInsertTaskUseCase(impl: InsertTaskUseCaseImpl): InsertTaskUseCase
}
