package com.example.presenter.viewmodel

import androidx.lifecycle.*
import com.example.domain.usecase.todo.GetAllToDoUseCase
import com.example.domain.usecase.todo.InsertToDoUseCase
import com.example.domain.usecase.todo.RemoveToDoUseCase
import com.example.presenter.base.BaseViewModel
import com.example.presenter.model.ToDoItem
import com.example.presenter.model.ConvertToDoItemMapper
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase,
    private val insertToDoUseCase: InsertToDoUseCase,
    private val removeToDoUseCase: RemoveToDoUseCase,
    private val convertToDoItemMapper: ConvertToDoItemMapper
) : BaseViewModel(){

    private val _insertObserver = SingleLiveData<Unit>()
    val insertObserver : SingleLiveData<Unit>
        get() = _insertObserver

    private val _removeObserver = SingleLiveData<Unit>()
    val removeObserver : SingleLiveData<Unit>
        get() = _removeObserver

    private lateinit var _allTodoObserver : MutableLiveData<List<ToDoItem>>
    val allTodoObserver : LiveData<List<ToDoItem>>
        get() = _allTodoObserver

    init {
        getAllToDo()
    }

    fun insertTodo(toDo: ToDoItem) {
        launchTaskSync(
            onRequest = {
                insertToDoUseCase.execute(InsertToDoUseCase.Params(
                    convertToDoItemMapper.mapToDoMain(toDo)
                ))
            },
            onSuccess = {
                _insertObserver.postValue(Unit)
            },
            onError = {
            }
        )
    }

    fun removeTodo(toDo: ToDoItem) {
        launchTaskSync(
            onRequest = {
                removeToDoUseCase.execute(RemoveToDoUseCase.Params(
                    convertToDoItemMapper.mapToDoMain(toDo)
                ))
            },
            onSuccess = {
                _removeObserver.postValue(Unit)
            },
            onError = {
            }
        )
    }

    fun getAllToDo() = launchTaskSync(
        onRequest = {
            getAllToDoUseCase.execute()
        }, onSuccess = { flowListToDo ->
            _allTodoObserver = flowListToDo.map { domain ->
                domain.map { convertToDoItemMapper.mapToPresenter(it) }
            }.asLiveData() as MutableLiveData<List<ToDoItem>>
        }
    )
}
