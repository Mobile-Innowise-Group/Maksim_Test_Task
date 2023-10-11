package com.innowise.test.currency.presentation.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class FilterState : Parcelable {
    data object PriceFilterAsc : FilterState()
    data object PriceFilterDesc : FilterState()
    data class TagFilter(
        val tags: MutableSet<String> = mutableSetOf(
            TokenType.NATIVE.value,
            TokenType.ERC_20.value,
            TokenType.TRC_20.value,
        )
    ) : FilterState()
}

enum class TokenType(val value: String) {
    NATIVE("Native"),
    ERC_20("ERC-20"),
    TRC_20("TRC-20"),
}