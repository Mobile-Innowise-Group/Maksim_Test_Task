package com.innowise.test.currency.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innowise.test.currency.domain.CurrencyRateDomainEntity
import com.innowise.test.currency.domain.GetCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CurrencyRateViewModel @Inject constructor(
    private val getCurrency: GetCurrencyRatesUseCase,
) : ViewModel() {

    val rates: LiveData<List<CurrencyRateDomainEntity>>
        get() = _rates
    private val _rates by lazyOf(MutableLiveData<List<CurrencyRateDomainEntity>>(emptyList()))

    private val disposable: CompositeDisposable by lazyOf(CompositeDisposable())

    init {
        getRates()
    }

    private fun getRates() {
        disposable.add(
            getCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { rate ->
                        _rates.value = rate.rates
                    },
                    { error ->
                        Log.e("error", "${error.stackTrace}")
                    }
                )
        )
    }


}