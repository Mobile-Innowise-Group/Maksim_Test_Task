package com.innowise.test.currency.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innowise.test.currency.domain.CurrencyRateDomainEntity
import com.innowise.test.currency.domain.GetCurrencyRatesUseCase
import com.innowise.test.currency.presentation.dialog.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CurrencyRateViewModel @Inject constructor(
    private val getCurrency: GetCurrencyRatesUseCase,
) : ViewModel() {

    private val rates: MutableList<CurrencyRateDomainEntity> = mutableListOf()

    val filteredRates: LiveData<List<CurrencyRateDomainEntity>>
        get() = _filteredRates
    private val _filteredRates by lazyOf(MutableLiveData<List<CurrencyRateDomainEntity>>())

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
                        rates.addAll(rate.rates)
                        _filteredRates.value = rates
                    },
                    { error ->
                        Log.e("error", "${error.stackTrace}")
                    }
                )
        )
    }

    fun onFilterApplied(filterState: FilterState) {
        _filteredRates.value = when (filterState) {
            FilterState.PriceFilterAsc -> rates.sortedBy { it.equaivalent }
            FilterState.PriceFilterDesc -> rates.sortedByDescending { it.equaivalent }
            is FilterState.TagFilter ->
                rates.filter {
                    it.tag in filterState.tags
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}