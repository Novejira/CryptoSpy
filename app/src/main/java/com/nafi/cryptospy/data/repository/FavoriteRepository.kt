package com.nafi.cryptospy.data.repository

import com.nafi.cryptospy.data.datasource.favorite.FavoriteDataSource
import com.nafi.cryptospy.data.mapper.toFavoriteEntity
import com.nafi.cryptospy.data.mapper.toFavoriteList
import com.nafi.cryptospy.data.model.Detail
import com.nafi.cryptospy.data.model.Favorite
import com.nafi.cryptospy.data.source.local.entity.FavoriteEntity
import com.nafi.cryptospy.utils.ResultWrapper
import com.nafi.cryptospy.utils.proceed
import com.nafi.cryptospy.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

interface FavoriteRepository {
    fun getAllFavorite(): Flow<ResultWrapper<List<Favorite>>>

    fun checkFavoriteById(coinId: String): Flow<List<FavoriteEntity>>

    fun addFavorite(detail: Detail): Flow<ResultWrapper<Boolean>>

    fun deleteFavorite(favorite: Favorite): Flow<ResultWrapper<Boolean>>

    fun removeFavorite(coinId: String): Flow<ResultWrapper<Boolean>>

    fun deleteAll(): Flow<ResultWrapper<Boolean>>
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

    override fun checkFavoriteById(coinId: String): Flow<List<FavoriteEntity>> {
        return datasource.checkFavoriteById(coinId)
    }

    override fun addFavorite(detail: Detail): Flow<ResultWrapper<Boolean>> {
        return detail.id.let { coinId ->
            proceedFlow {
                val affectedRow =
                    datasource.addFavorite(
                        FavoriteEntity(
                            coinId = coinId,
                            coinImg = detail.image,
                            coinSymbol = detail.webSlug,
                            coinName = detail.name,
                        ),
                    )
                delay(2000)
                affectedRow > 0
            }
        }
    }

    override fun deleteFavorite(favorite: Favorite): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { datasource.deleteFavorite(favorite.toFavoriteEntity()) > 0 }
    }

    override fun removeFavorite(coinId: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { datasource.removeFavorite(coinId) > 0 }
    }

    override fun deleteAll(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            datasource.deleteAll()
            true
        }
    }
}
