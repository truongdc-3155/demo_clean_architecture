package com.example.presenter.viewmodel

import androidx.lifecycle.*
import com.example.domain.usecase.todo.GetToDoUseCase
import com.example.presenter.base.BaseViewModel
import com.example.presenter.model.ToDoItem
import com.example.presenter.model.ToDoItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val toDoItemMapper: ToDoItemMapper,
    private val getToDoUseCase: GetToDoUseCase
) : BaseViewModel(){

    fun insertTodo(toDo: ToDoItem) {
        launchTaskSync(
            onRequest = {
                getToDoUseCase.insertTodo(toDoItemMapper.mapToDoMain(toDo))
            }
        )
    }

    fun removeTodo(toDo: ToDoItem) {
        launchTaskSync(
            onRequest = {
                getToDoUseCase.deleteTodo(toDoItemMapper.mapToDoMain(toDo))
            }
        )
    }

    fun getAll() = getToDoUseCase.getAllTodo().map { domain ->
        domain.map { toDoItemMapper.mapToPresenter(it) }
    }.asLiveData()
}
