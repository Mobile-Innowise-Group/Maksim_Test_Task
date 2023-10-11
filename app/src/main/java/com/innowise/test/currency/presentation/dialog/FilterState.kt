package com.innowise.test.currency.presentation.dialog

sealed class FilterState {
    object PriceFilterAsc : FilterState()
    object PriceFilterDesc : FilterState()
    data class TagFilter(
        val list: List<String> = listOf(
            TokenType.NATIVE.name,
            TokenType.ECR_20.name,
            TokenType.TCR_20.name,
        )
    ) : FilterState()
}

enum class TokenType {
    NATIVE,
    ECR_20,
    TCR_20,
}