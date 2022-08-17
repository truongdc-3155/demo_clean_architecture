package com.example.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T>launchTaskSync(
        onRequest : suspend CoroutineScope.() -> DataResult<T>,
        onSuccess : (T) -> Unit = {},
        onError : (Exception) -> Unit = {},
    )= viewModelScope.launch{

        when (val asynchronousTasks = onRequest(this)) {
            is DataResult.Success -> {
                onSuccess(asynchronousTasks.data)
            }

            is DataResult.Error -> {
                onError(asynchronousTasks.exception)
            }
        }
    }
}
