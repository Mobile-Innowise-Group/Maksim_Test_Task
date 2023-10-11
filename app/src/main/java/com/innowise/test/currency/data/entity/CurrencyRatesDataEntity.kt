package com.innowise.test.currency.data.entity


import com.google.gson.annotations.SerializedName
import com.innowise.test.currency.domain.CurrencyRateDomainEntity
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRatesDataEntity(
    @SerializedName("items")
    val rates: List<CurrencyRateDataEntity>
)