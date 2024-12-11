package com.heaven.templateproject.presentation.viewmodels.base

sealed class ScreenState<out T: Any> {
    data class Content<out T: Any>(val data: T) : ScreenState<T>()
    data object Loading : ScreenState<Nothing>()
    data object Error : ScreenState<Nothing>()
}