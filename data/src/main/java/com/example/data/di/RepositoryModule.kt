package com.example.data.di

import com.example.data.repository.ToDoRepositoryImpl
import com.example.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providerUserRepository(repository: ToDoRepositoryImpl): ToDoRepository {
        return repository
    }
}
