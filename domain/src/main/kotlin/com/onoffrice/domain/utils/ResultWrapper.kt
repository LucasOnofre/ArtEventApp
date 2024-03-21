package com.onoffrice.domain.utils

import com.onoffrice.domain.model.ApiError

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val code: Int? = null, val error: ApiError? = null) :
        ResultWrapper<Nothing>()
    data class GenericError(val code: Int? = null, val error: ApiError? = null) :
        ResultWrapper<Nothing>()

    data class UndefinedError(val exception: Throwable? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}
