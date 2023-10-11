package com.innowise.test.currency.data

import com.innowise.test.currency.domain.CurrencyRateDomainEntity
import com.innowise.test.currency.domain.CurrencyRatesDomainEntity
import com.innowise.test.currency.domain.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CurrencyRateRepositoryImpl @Inject constructor(
    private val dataSource: FakeCurrencyRateDataSource
) : CurrencyRatesRepository {

    override fun getRates(): Single<CurrencyRatesDomainEntity> =
        dataSource
            .getRates()
            .map { result ->
                CurrencyRatesDomainEntity(
                    rates = result.rates.map {
                        CurrencyRateDomainEntity(
                            balance = it.balance,
                            tag = it.tag,
                            equivalent = it.equaivalent,
                            imageUrl = it.imageUrl,
                            symbol = it.symbol,
                        )
                    })
            }
}