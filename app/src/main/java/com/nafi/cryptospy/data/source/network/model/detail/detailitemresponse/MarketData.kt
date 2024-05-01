package com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse

import com.google.gson.annotations.SerializedName

data class MarketData(
    @SerializedName("current_price")
    var currentPrice: CurrentPrice? = CurrentPrice(),
)
