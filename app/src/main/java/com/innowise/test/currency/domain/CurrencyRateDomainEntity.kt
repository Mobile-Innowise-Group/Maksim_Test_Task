package com.innowise.test.currency.domain

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRateDomainEntity(
    val balance: Double,
    val equaivalent: Double,
    val imageUrl: String,
    val symbol: String,
    val tag: String
)