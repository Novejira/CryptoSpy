package com.nafi.cryptospy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "coin_id")
    var coinId: String?,
    @ColumnInfo(name = "coin_symbol")
    var coinSymbol: String?,
    @ColumnInfo(name = "coin_name")
    var coinName: String?,
    @ColumnInfo(name = "coin_img")
    var coinImg: String?,
)
