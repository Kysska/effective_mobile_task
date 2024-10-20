package com.example.ui.view

sealed class ViewState<out T> {
    data class Success<out T>(val data: T) : ViewState<T>()
    object Loading : ViewState<Nothing>()
    data class Error(val throwable: Throwable) : ViewState<Nothing>()

    fun getDataOrNull(): T? = if (this is Success) data else null

    val isSuccess: Boolean
        get() = this is Success

    val isLoading: Boolean
        get() = this is Loading

    val errorMessage: String?
        get() = if (this is Error) throwable.message else null

    val shouldShowErrorMessage: Boolean
        get() = this is Error && throwable.message != null
}
