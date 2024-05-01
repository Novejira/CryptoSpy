package com.nafi.cryptospy.data.repository

import com.nafi.cryptospy.data.datasource.DetailDataSource
import com.nafi.cryptospy.data.mapper.toDetail
import com.nafi.cryptospy.data.model.Detail
import com.nafi.cryptospy.utils.ResultWrapper
import com.nafi.cryptospy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getDetail(id: String): Flow<ResultWrapper<Detail>>
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override fun getDetail(id: String): Flow<ResultWrapper<Detail>> {
        return proceedFlow {
            dataSource.getDetail(id).toDetail()
        }
    }
}
