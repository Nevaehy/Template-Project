package com.heaven.templateproject.network.responseHandling

sealed class RestApiResponse<out T : Any?> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : RestApiResponse<T>()

    /**
     * Failure response with body
     */
    data class ApiError(val body: RestApiError, val code: Int) : RestApiResponse<Nothing>()

    /**
     * Network error
     */
    data class NetworkError(val error: Exception) : RestApiResponse<Nothing>()

    data object AuthorizationError : RestApiResponse<Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?, val code: Int) : RestApiResponse<Nothing>()
}