package io.ebrahim.coin.model

data class CoinResponse(
    val result: List<Coin>,
    val meta: Meta
)

data class Meta(
    val page: Int,
    val limit: Int,
    val itemCount: Int,
    val pageCount: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)