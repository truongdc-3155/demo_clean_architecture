package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.DbConfig
import com.example.data.database.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ToDoDatabase::class.java, DbConfig.DATABASE_NAME)
            .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideToDoDao(db: ToDoDatabase) = db.toDoDao()
}
