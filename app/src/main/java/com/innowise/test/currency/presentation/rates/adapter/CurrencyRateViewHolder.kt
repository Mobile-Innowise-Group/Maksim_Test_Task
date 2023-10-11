package com.innowise.test.currency.presentation.rates.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.innowise.test.currencty.R
import com.innowise.test.currencty.databinding.ListItemBinding
import com.innowise.test.currency.di.GlideApp
import com.innowise.test.currency.domain.CurrencyRateDomainEntity

class CurrencyRateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding by viewBinding(ListItemBinding::bind)

    fun bind(currencyRate: CurrencyRateDomainEntity) {
        with(viewBinding) {
            Glide
                .with(itemView.context)
                .load(currencyRate.imageUrl)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
            tag.text = currencyRate.tag
            equaivalent.text = currencyRate.equaivalent.toString()
            balance.text = currencyRate.balance.toString()
            currencyName.text = currencyRate.symbol
        }
    }
}