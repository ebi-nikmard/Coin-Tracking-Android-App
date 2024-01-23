package io.ebrahim.coin.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.ebrahim.coin.ui.CoinDetailsActivity
import io.ebrahim.coin.R
import io.ebrahim.coin.model.Coin
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * RecyclerView Adapter for displaying a list of coins.
 */
class CoinAdapter(private val context: Context, private var coins: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder for the item view.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the data to the views of the item view.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coins[position]

        // Load coin avatar using Picasso
        Picasso.get().load(coin.icon).into(holder.coinAvatar)

        // Bind other coin details to the views as needed
        holder.coinRank.text = coin.rank.toString()
        holder.coinName.text = coin.name
        holder.coinSymbol.text = coin.symbol
        holder.coinPrice.text = "$ " + BigDecimal(coin.price).setScale(2, RoundingMode.HALF_UP).toString()
        holder.coinChange.text = coin.priceChange1h.toString() + " %"

        // Set color based on price change
        if (coin.priceChange1h > 0) holder.coinChange.setTextColor(context.getColor(R.color.green))
        if (coin.priceChange1h < 0) holder.coinChange.setTextColor(context.getColor(R.color.red))

        // Set OnClickListener for opening CoinDetailsActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CoinDetailsActivity::class.java)

            // Pass relevant coin details to the CoinDetailsActivity
            intent.putExtra("coinId", coin.id)
            intent.putExtra("coinSymbol", coin.symbol)
            intent.putExtra("coinIcon", coin.icon)
            intent.putExtra("redditUrl", coin.redditUrl)
            intent.putExtra("websiteUrl", coin.websiteUrl)
            intent.putExtra("twitterUrl", coin.twitterUrl)
            intent.putExtra("coinPrice", BigDecimal(coin.price).setScale(2, RoundingMode.HALF_UP).toString())
            intent.putExtra("priceChange1h", coin.priceChange1h.toString())
            intent.putExtra("coinPriceBTC", BigDecimal(coin.priceBtc).setScale(7, RoundingMode.HALF_UP).toString())
            intent.putExtra("coinVolume", BigDecimal(coin.volume).setScale(2, RoundingMode.HALF_UP).toString())
            intent.putExtra("marketCap", BigDecimal(coin.marketCap).setScale(2, RoundingMode.HALF_UP).toString())
            intent.putExtra("totalSupply", BigDecimal(coin.totalSupply).setScale(2, RoundingMode.HALF_UP).toString())

            context.startActivity(intent)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int {
        return coins.size
    }

    /**
     * Updates the list of coins and notifies the adapter about the data change.
     */
    fun submitList(newList: List<Coin>) {
        coins = newList
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coinRank: TextView = itemView.findViewById(R.id.coinRank)
        val coinAvatar: ImageView = itemView.findViewById(R.id.coinAvatar)
        val coinName: TextView = itemView.findViewById(R.id.coinName)
        val coinSymbol: TextView = itemView.findViewById(R.id.coinSymbol)
        val coinPrice: TextView = itemView.findViewById(R.id.coinPrice)
        val coinChange: TextView = itemView.findViewById(R.id.coinChange)
    }
}
