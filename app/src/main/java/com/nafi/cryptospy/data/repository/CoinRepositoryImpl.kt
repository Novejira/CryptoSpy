package com.nafi.cryptospy.data.repository

import com.nafi.cryptospy.data.datasource.coin.CoinDataSource
import com.nafi.cryptospy.data.mapper.toCoinList
import com.nafi.cryptospy.data.model.Coin
import com.nafi.cryptospy.utils.ResultWrapper
import com.nafi.cryptospy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class CoinRepositoryImpl(private val dataSource: CoinDataSource) : CoinRepository {
    override fun getCoins(): Flow<ResultWrapper<List<Coin>>> {
        return proceedFlow {
            dataSource.getCoinList().toCoinList()
        }
    }
}
