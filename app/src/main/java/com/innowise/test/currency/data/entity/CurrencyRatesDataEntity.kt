package com.innowise.test.currency.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRatesDataEntity(
    @SerializedName("items")
    val rates: List<CurrencyRateDataEntity>
)