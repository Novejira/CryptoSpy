package com.nafi.cryptospy.data.datasource.coin

import com.nafi.cryptospy.data.source.network.model.coin.CoinListResponse

interface CoinDataSource {
    suspend fun getCoinList(): List<CoinListResponse>
}
