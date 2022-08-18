package com.example.domain.usecase.todo

import com.example.domain.model.ToDo
import com.example.domain.repository.ToDoRepository
import com.example.domain.usecase.WithoutParamUseCase
import com.example.domain.utils.DataResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetAllToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
): WithoutParamUseCase<Flow<List<ToDo>>>() {

    override suspend fun execute(): DataResult<Flow<List<ToDo>>> {
        return withContextResult {
            toDoRepository.getAllTodo()
        }
    }
}
