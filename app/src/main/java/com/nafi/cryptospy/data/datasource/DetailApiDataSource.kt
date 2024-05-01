package com.nafi.cryptospy.data.datasource

import com.nafi.cryptospy.data.source.network.model.detail.DetailResponse
import com.nafi.cryptospy.data.source.network.service.CryptoSpyApiService

class DetailApiDataSource(private val service: CryptoSpyApiService) : DetailDataSource {
    override suspend fun getDetail(id: String): DetailResponse {
        return service.getDetail(id = id)
    }
}
