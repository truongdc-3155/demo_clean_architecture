package com.example.data.repository

import com.example.data.model.ToDoEntityMapper
import com.example.data.source.ToDoDataSource
import com.example.domain.model.ToDo
import com.example.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val localTodo: ToDoDataSource.Local,
    private val remoteTodo: ToDoDataSource.Remote,
    private val toDoEntityMapper: ToDoEntityMapper
) : ToDoRepository {

    override suspend fun insertTodo(todo: ToDo) {
        localTodo.insertTodo(toDoEntityMapper.mapToEntity(todo))
    }

    override suspend fun deleteTodo(todo: ToDo) {
        localTodo.deleteTodo(toDoEntityMapper.mapToEntity(todo))
    }

    override fun getAllTodo(): Flow<List<ToDo>> {
        return localTodo.getAllTodo().map {
            it.map { domain -> toDoEntityMapper.mapToDomain(domain) }
        }
    }
}
