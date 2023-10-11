package com.innowise.test.currency.domain

import io.reactivex.rxjava3.core.Single

interface CurrencyRatesRepository {
    fun getRates(): Single<CurrencyRatesDomainEntity>
}