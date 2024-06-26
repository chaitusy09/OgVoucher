package com.oginnovation.voucherssas.token

import com.google.gson.annotations.SerializedName

data class TokenBody(
    @SerializedName("code")
    val code: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("redirectUrl")
    val redirectUrl: String
)