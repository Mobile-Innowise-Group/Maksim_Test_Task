package com.innowise.test.currency.di

import com.innowise.test.currency.data.CurrencyRateRepositoryImpl
import com.innowise.test.currency.data.FakeCurrencyRateDataSource
import com.innowise.test.currency.domain.CurrencyRatesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: CurrencyRateRepositoryImpl
    ): CurrencyRatesRepository

}

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    fun provideDataSource(): FakeCurrencyRateDataSource = FakeCurrencyRateDataSource()
}