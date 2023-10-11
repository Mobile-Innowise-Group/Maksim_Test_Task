package com.innowise.test.currency.data.entity


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRateDataEntity(
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("equaivalent")
    val equaivalent: Double,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("tag")
    val tag: String
)