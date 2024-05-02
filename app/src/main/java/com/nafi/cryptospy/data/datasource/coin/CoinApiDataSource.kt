package com.nafi.cryptospy.data.datasource.coin

import com.nafi.cryptospy.data.source.network.model.coin.CoinListResponse
import com.nafi.cryptospy.data.source.network.service.CryptoSpyApiService

class CoinApiDataSource(private val service: CryptoSpyApiService) : CoinDataSource {
    override suspend fun getCoinList(): List<CoinListResponse> {
        return service.getCoins()
    }
}
