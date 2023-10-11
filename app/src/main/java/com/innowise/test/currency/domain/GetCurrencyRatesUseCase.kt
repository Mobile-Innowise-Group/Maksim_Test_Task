package com.innowise.test.currency.domain

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrencyRatesUseCase @Inject constructor(private val currencyRatesRepository: CurrencyRatesRepository) {
    operator fun invoke(): Single<CurrencyRatesDomainEntity> = currencyRatesRepository.getRates()
}