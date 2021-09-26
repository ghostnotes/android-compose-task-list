package co.ghostnotes.sample.compose.tasklist.di

import co.ghostnotes.sample.compose.tasklist.data.usecase.FindTasksUseCaseImpl
import co.ghostnotes.sample.compose.tasklist.domain.usecase.FindTasksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindFindTasksUseCase(impl: FindTasksUseCaseImpl): FindTasksUseCase

}