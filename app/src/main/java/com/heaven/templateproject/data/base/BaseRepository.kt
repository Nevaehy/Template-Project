package com.heaven.templateproject.data.base

import com.heaven.templateproject.network.responseHandling.RestApiResponse


abstract class BaseRepository {

    fun <T : Any> handleRestResponse(response: RestApiResponse<T>): T {
        return when (response) {
            is RestApiResponse.ApiError -> {
                val errorMessage = response.body.error?.reason
                    ?: response.body.error?.message
                    ?: response.body.message
                    ?: ""

                val errorCode = response.body.error?.code ?: response.code
                throw RestApiException(errorMessage, errorCode)
            }
            is RestApiResponse.AuthorizationError -> throw AuthorizationException()
            is RestApiResponse.NetworkError -> throw NetworkErrorException()
            is RestApiResponse.UnknownError -> {
                throw RestApiException("oops something went wrong [${response.code}]", response.code)
            }
            is RestApiResponse.Success -> response.body
        }
    }
}