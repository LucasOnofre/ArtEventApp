package com.onoffrice.core.utils

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    object Idle : UIState<Nothing>()
    object NetworkError : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class UndefinedError(val throwable: Throwable? = null) : UIState<Nothing>()
    data class Error(val error: com.onoffrice.domain.model.ApiError?) : UIState<Nothing>()
    data class GenericError(val error: com.onoffrice.domain.model.ApiError?) : UIState<Nothing>()
}
