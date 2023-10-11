package com.innowise.test.currency.data

import com.google.gson.Gson
import com.innowise.test.currency.data.entity.CurrencyRatesDataEntity
import com.innowise.test.currency.data.entity.CurrencyRatesResponse
import com.innowise.test.currency.data.mock.FakeResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FakeCurrencyRateDataSource @Inject constructor() {
    fun getRates(): Single<CurrencyRatesDataEntity> = Single.create { emitter ->
        try {
            val gson = Gson()
            val response =
                gson.fromJson(FakeResponse.responseBody, CurrencyRatesResponse::class.java)
            emitter.onSuccess(response.result)
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}