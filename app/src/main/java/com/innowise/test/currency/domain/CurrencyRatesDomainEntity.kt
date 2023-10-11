package com.innowise.test.currency.domain

import com.innowise.test.currency.data.entity.CurrencyRateDataEntity


data class CurrencyRatesDomainEntity(
    val rates: List<CurrencyRateDomainEntity>
)