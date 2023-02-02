package com.psw9999.composesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateViewModel : ViewModel() {

    private val _responseState: MutableStateFlow<ResponseState<Boolean>> = MutableStateFlow(ResponseState.Init)
    val responseState = _responseState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _responseState.emit(
                ResponseState.Success(true)
            )
        }
    }
}

sealed class ResponseState<out T> {
    object Init: ResponseState<Nothing>()
    object Loading: ResponseState<Nothing>()
    data class Success<T>(val data: T): ResponseState<T>()
    object Error: ResponseState<Nothing>()
}