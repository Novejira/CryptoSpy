package com.nafi.cryptospy.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nafi.cryptospy.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FAVORITE")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM FAVORITE WHERE coin_id = :coinId ")
    fun checkFavoriteById(coinId: String): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity): Long

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity): Int

    @Query("DELETE FROM FAVORITE WHERE coin_id = :coinId")
    suspend fun removeFavorite(coinId: String): Int

    @Query("DELETE FROM FAVORITE")
    suspend fun deleteAll()
}
