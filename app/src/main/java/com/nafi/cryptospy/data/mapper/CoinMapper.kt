package com.nafi.cryptospy.data.mapper

import com.nafi.cryptospy.data.model.Coin
import com.nafi.cryptospy.data.source.network.model.coin.CoinListResponse

fun CoinListResponse?.toCoin() =
    Coin(
        id = this?.id.orEmpty(),
        imgUrl = this?.image.orEmpty(),
        name = this?.name.orEmpty(),
        symbol = this?.symbol.orEmpty(),
        price = this?.currentPrice ?: 0.0,
    )

fun Collection<CoinListResponse>?.toCoinList() =
    this?.map {
        it.toCoin()
    } ?: listOf()
