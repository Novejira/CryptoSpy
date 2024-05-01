package com.nafi.cryptospy.data.source.network.model.detail.detailitemresponse

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("large") var large: String? = null,
)
