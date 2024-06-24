package com.oginnovation.voucherssas.vouchers.model

import android.os.Parcelable
import java.io.Serializable


data class ChargerStation(
    val station_name: String,
    val station_phone: String?, // Allow null for phone number
    val longitude: Double,
    val latitude: Double, // Reuse the Location class from previous example
    val address: String,
    val city: String,
    val state: String,
    val zipcode: String,
    val country: String
):Serializable
data class ChargerListInfo(
    val ChargerListInfo: ArrayList<ChargerStation>,
    val ResultInfo: ResultInfo
) : Serializable

data class ResultInfo(
    val Message: String,
    val IsSuccessful: Boolean
):Serializable