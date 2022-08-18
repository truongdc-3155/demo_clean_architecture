package com.example.presenter.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.DataResult
import com.truongdc21.mediatree.utils.livedata.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    /**
     * Need handle convert Exception to String let show for user
     */
    private val _exceptionObserver = SingleLiveData<Exception>()
    val exceptionObserver : SingleLiveData<Exception>
        get() = _exceptionObserver

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
                /** Handle Exception **/
                _exceptionObserver.postValue(asynchronousTasks.exception)
                onError(asynchronousTasks.exception)
            }
        }
    }
}
