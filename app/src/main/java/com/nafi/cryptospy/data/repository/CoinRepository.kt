package com.nafi.cryptospy.data.repository

import com.nafi.cryptospy.data.model.Coin
import com.nafi.cryptospy.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(): Flow<ResultWrapper<List<Coin>>>
}
