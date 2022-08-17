package com.example.data.source

import com.example.data.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoDataSource {

    interface Local {

        suspend fun insertTodo(todo: ToDoEntity)

        suspend fun deleteTodo(todo: ToDoEntity)

        fun getAllTodo(): Flow<List<ToDoEntity>>
    }

    interface Remote {
    }
}
