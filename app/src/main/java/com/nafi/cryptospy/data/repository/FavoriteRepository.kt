package com.nafi.cryptospy.data.repository

import com.nafi.cryptospy.data.datasource.favorite.FavoriteDataSource
import com.nafi.cryptospy.data.mapper.toFavoriteEntity
import com.nafi.cryptospy.data.mapper.toFavoriteList
import com.nafi.cryptospy.data.model.Favorite
import com.nafi.cryptospy.data.source.local.entity.FavoriteEntity
import com.nafi.sfocryptospyods.utils.ResultWrapper
import com.nafi.sfocryptospyods.utils.proceed
import com.nafi.sfocryptospyods.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

interface FavoriteRepository {
    fun getAllFavorite(): Flow<ResultWrapper<List<Favorite>>>

    fun checkFavoriteById(coinId : String) : Boolean

    suspend fun addFavorite(favorite: Favorite): Long

    suspend fun deleteFavorite(favorite: Favorite): Flow<ResultWrapper<Boolean>>

    suspend fun deleteAll(): Flow<ResultWrapper<Boolean>>
}

class FavoriteRepositoryImpl(private val datasource: FavoriteDataSource) : FavoriteRepository {
    override fun getAllFavorite(): Flow<ResultWrapper<List<Favorite>>> {
        return datasource.getAllFavorite()
            .map {
                proceed {
                    val result = it.toFavoriteList()
                    result
                }
            }.map {
                if (it.payload?.isEmpty() == false) return@map it
                ResultWrapper.Empty(it.payload)
            }.onStart {
                emit(ResultWrapper.Loading())
                delay(2000)
            }
    }

    override fun checkFavoriteById(coinId: String): Boolean {
        return datasource.checkFavoriteById(coinId)
    }

    override suspend fun addFavorite(favorite: Favorite): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavorite(favorite: Favorite): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { datasource.deleteFavorite(favorite.toFavoriteEntity()) > 0 }
    }

    override suspend fun deleteAll(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            datasource.deleteAll()
            true
        }
    }

}