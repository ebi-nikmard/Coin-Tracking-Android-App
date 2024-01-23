package io.ebrahim.coin.model
/**
 * Data class representing information about a crypto coins.
 */
data class Coin(
    val id: String,
    val icon: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val priceBtc: Double,
    val volume: Double,
    val marketCap: Double,
    val availableSupply: Long,
    val totalSupply: Long,
    val priceChange1h: Double,
    val priceChange1d: Double,
    val priceChange1w: Double,
    val redditUrl: String,
    val websiteUrl: String,
    val twitterUrl: String,
    val explorers: List<String>
)