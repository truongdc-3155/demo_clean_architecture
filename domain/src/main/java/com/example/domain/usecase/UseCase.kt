package com.example.domain.usecase

import com.example.domain.utils.ContextResult
import com.example.domain.utils.DataResult

abstract class UseCase <in Params, out T> : ContextResult() {
    abstract suspend fun execute(params: Params? = null): DataResult<T>
}

abstract class WithoutParamUseCase <out T> : ContextResult() {
    abstract suspend fun execute(): DataResult<T>
}
