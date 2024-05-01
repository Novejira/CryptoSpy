package com.nafi.cryptospy.data.datasource

import com.nafi.cryptospy.data.source.network.model.detail.DetailResponse

interface DetailDataSource {
    suspend fun getDetail(id: String): DetailResponse
}
