package com.heaven.templateproject.network.response

import com.google.gson.annotations.SerializedName
import com.heaven.templateproject.network.responseHandling.RestApiError

open class BaseResponse<T> {
    @SerializedName("data")
    var data: T? = null

    @SerializedName("error")
    var error: RestApiError? = null

    @SerializedName("success")
    var isSuccess: Boolean = false
}