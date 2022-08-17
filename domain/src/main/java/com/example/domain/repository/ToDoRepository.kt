package com.example.domain.repository

import com.example.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository : Repository {

    suspend fun insertTodo(todo: ToDo)

    suspend fun deleteTodo(todo: ToDo)

    fun getAllTodo(): Flow<List<ToDo>>

}
