package com.nafi.cryptospy.data.mapper

import com.nafi.cryptospy.data.model.Favorite
import com.nafi.cryptospy.data.source.local.entity.FavoriteEntity

fun Favorite?.toFavoriteEntity() =
    FavoriteEntity(
        id = this?.id,
        coinId = this?.coinId,
        coinSymbol = this?.coinSymbol,
        coinName = this?.coinName,
        coinImg = this?.coinImg
    )

fun FavoriteEntity?.toFavorite() =
    Favorite(
        id = this?.id,
        coinId = this?.coinId,
        coinSymbol = this?.coinSymbol,
        coinName = this?.coinName,
        coinImg = this?.coinImg
    )

fun List<FavoriteEntity>.toFavoriteList() = this.map { it.toFavorite() }
