package com.example.data.source.local

import com.example.data.database.ToDoDao
import com.example.data.model.ToDoEntity
import com.example.data.source.ToDoDataSource
import kotlinx.coroutines.flow.Flow


class ToDoLocalSource(private val toDoDao: ToDoDao) : ToDoDataSource.Local {

    override suspend fun insertTodo(todo: ToDoEntity) {
        toDoDao.insertToDo(todo)
    }

    override suspend fun deleteTodo(todo: ToDoEntity) {
        toDoDao.deleteToDo(todo)
    }

    override fun getAllTodo(): Flow<List<ToDoEntity>> {
        return toDoDao.getAllToDos()
    }
}
