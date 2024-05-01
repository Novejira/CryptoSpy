package com.nafi.cryptospy.data.datasource.favorite

import com.nafi.cryptospy.data.source.local.dao.FavoriteDao
import com.nafi.cryptospy.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteDataSource {
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    fun checkFavoriteById(coinId: String): Flow<List<FavoriteEntity>>

    suspend fun addFavorite(favorite: FavoriteEntity): Long

    suspend fun deleteFavorite(favorite: FavoriteEntity): Int

    suspend fun removeFavorite(coinId: String): Int

    suspend fun deleteAll()
}

class FavoriteDataSourceImpl(private val dao: FavoriteDao) : FavoriteDataSource {
    override fun getAllFavorite(): Flow<List<FavoriteEntity>> = dao.getAllFavorite()

    override fun checkFavoriteById(coinId: String): Flow<List<FavoriteEntity>> = dao.checkFavoriteById(coinId)

    override suspend fun addFavorite(favorite: FavoriteEntity): Long = dao.addFavorite(favorite)

    override suspend fun deleteFavorite(favorite: FavoriteEntity): Int = dao.deleteFavorite(favorite)

    override suspend fun removeFavorite(coinId: String): Int = dao.removeFavorite(coinId)

    override suspend fun deleteAll() = dao.deleteAll()
}
