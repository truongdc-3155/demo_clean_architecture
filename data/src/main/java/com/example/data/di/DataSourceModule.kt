package com.example.data.di

import com.example.data.database.ToDoDao
import com.example.data.source.ToDoDataSource
import com.example.data.source.local.ToDoLocalSource
import com.example.data.source.remote.ToDoRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideToDoDataSourceLocal(dao: ToDoDao): ToDoDataSource.Local {
        return ToDoLocalSource(dao)
    }

    @Singleton
    @Provides
    fun provideToDoDataSourceRemote(): ToDoDataSource.Remote {
        return ToDoRemoteSource()
    }
}
