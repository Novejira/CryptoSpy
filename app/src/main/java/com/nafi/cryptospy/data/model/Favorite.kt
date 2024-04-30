package com.nafi.cryptospy.data.model

data class Favorite(
    var id: Int? = null,
    var coinId: String? = null,
    var coinSymbol: String?,
    var coinName: String?,
    var coinImg: String?
)
