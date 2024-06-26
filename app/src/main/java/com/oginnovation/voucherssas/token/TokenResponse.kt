package com.oginnovation.voucherssas.token

import com.google.gson.annotations.SerializedName

data class TokenResponse (
    val statusCode: Int,
    val message: String,
    val data: Data
)
data class Data(
    val message: String,
    val isAlreadySubscribed: Boolean,
    val accessToken: String
)