package com.innowise.test.currency.presentation.rates.adapter

import androidx.recyclerview.widget.DiffUtil
import com.innowise.test.currency.domain.CurrencyRateDomainEntity

class CurrencyRateDiffUtilCallback : DiffUtil.ItemCallback<CurrencyRateDomainEntity>() {

    override fun areItemsTheSame(oldItem: CurrencyRateDomainEntity, newItem: CurrencyRateDomainEntity) =
        oldItem.balance == newItem.balance

    override fun areContentsTheSame(oldItem: CurrencyRateDomainEntity, newItem: CurrencyRateDomainEntity) =
        oldItem == newItem
}