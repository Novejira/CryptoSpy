package com.nafi.cryptospy.data.source.network.model.detail

import com.google.gson.annotations.SerializedName
import com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse.Description
import com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse.Image
import com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse.MarketData

data class DetailResponse(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("web_slug")
    var webSlug: String? = null,
    @SerializedName("description")
    var description: Description? = Description(),
    @SerializedName("image")
    var image: Image? = Image(),
    @SerializedName("market_data")
    var marketData: MarketData? = MarketData(),
)
