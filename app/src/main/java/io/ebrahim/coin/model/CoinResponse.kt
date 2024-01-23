package io.ebrahim.coin.model

/**
 * Data class representing the response from the coin details API.
 */
data class CoinResponse(
    val result: List<Coin>,
    val meta: Meta
)

/**
 * Data class representing metadata information in the coin details API.
 */
data class Meta(
    val page: Int,
    val limit: Int,
    val itemCount: Int,
    val pageCount: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
