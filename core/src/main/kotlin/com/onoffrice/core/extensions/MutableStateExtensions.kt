package com.onoffrice.core.extensions

import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<UIState<T>>.wrapResponse(
    resultWrapper: ResultWrapper<T>) {
    when (resultWrapper) {
        is ResultWrapper.Success -> {
            handleState(UIState.Success(resultWrapper.value))
        }

        is ResultWrapper.Error -> {
            val error = resultWrapper.error
            handleState(UIState.Error(error))
        }

        is ResultWrapper.GenericError -> {
            val error = resultWrapper.error
            handleState(UIState.GenericError(error))
        }

        is ResultWrapper.NetworkError -> {
            handleState(UIState.NetworkError)
        }

        is ResultWrapper.UndefinedError -> {
            val throwable = resultWrapper.exception
            handleState(UIState.UndefinedError(throwable))
        }
    }
}

fun <T> MutableStateFlow<UIState<T>>.handleState(
    state: UIState<T>,
) {
    value = state
}