package com.heaven.templateproject.domain.base

import android.accounts.NetworkErrorException

sealed class Outcome<out T : Any> {
    data class Success<out T : Any>(val data: T) : Outcome<T>()
    data class Error(val errorCode: Int, val message: String) : Outcome<Nothing>()
    class NetworkConnection(val cause: NetworkErrorException? = null) : Outcome<Nothing>()
    data object Unauthorized : Outcome<Nothing>()
}