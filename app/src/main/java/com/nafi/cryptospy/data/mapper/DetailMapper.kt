package com.nafi.cryptospy.data.mapper

import com.nafi.cryptospy.data.model.Detail
import com.nafi.cryptospy.data.source.network.model.detail.DetailResponse

fun DetailResponse?.toDetail() =
    Detail(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        webSlug = this?.webSlug.orEmpty(),
        description = this?.description?.en.orEmpty(),
        image = this?.image?.large.orEmpty(),
        price = this?.marketData?.currentPrice?.usd ?: 0.0,
    )

fun Collection<DetailResponse>?.toCatalogs() =
    this?.map {
        it.toDetail()
    }
