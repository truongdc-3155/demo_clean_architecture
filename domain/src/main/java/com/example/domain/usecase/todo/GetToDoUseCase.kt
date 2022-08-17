package com.example.domain.usecase.todo

import com.example.domain.base.BaseUseCase
import com.example.domain.model.ToDo
import com.example.domain.repository.ToDoRepository

import javax.inject.Inject

open class GetToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) : BaseUseCase() {

    suspend fun insertTodo(todo: ToDo) = withContextResult {
        toDoRepository.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: ToDo) = withContextResult {
        toDoRepository.deleteTodo(todo)
    }

    fun getAllTodo() = toDoRepository.getAllTodo()
}
