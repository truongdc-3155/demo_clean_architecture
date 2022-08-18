package com.example.domain.usecase.todo

import com.example.domain.model.ToDo
import com.example.domain.repository.ToDoRepository
import com.example.domain.usecase.UseCase
import com.example.domain.utils.DataResult
import javax.inject.Inject

class InsertToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
): UseCase<InsertToDoUseCase.Params , Unit>() {

    override suspend fun execute(params: Params?): DataResult<Unit>{
        return withContextResult {
            params?.let {
                toDoRepository.insertTodo(it.toDo)
            }
        }
    }
    data class Params(val toDo : ToDo)
}
