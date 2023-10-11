package com.innowise.test.currency.presentation.rates.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.innowise.test.currencty.R
import com.innowise.test.currency.domain.CurrencyRateDomainEntity

class CurrencyRatesAdapter : ListAdapter<CurrencyRateDomainEntity, CurrencyRateViewHolder>(
    CurrencyRateDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}