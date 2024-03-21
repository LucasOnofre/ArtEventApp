package com.onoffrice.data.model

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("error") val error: String? = null
)
