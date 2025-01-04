package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<Model>(
    @SerializedName("message")
    val message: String?= null,
    @SerializedName("cod")
    var code: Int,
    @SerializedName("data")
    val data: Model
)
