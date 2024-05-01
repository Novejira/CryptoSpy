package com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse

import com.google.gson.annotations.SerializedName

data class CurrentPrice(
    @SerializedName("usd")
    var usd: Double? = null,
)
