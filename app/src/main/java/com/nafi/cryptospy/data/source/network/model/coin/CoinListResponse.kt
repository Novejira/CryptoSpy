package com.nafi.cryptospy.data.source.network.model.coin

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoinListResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("current_price")
    val currentPrice: Double? = null,
)
