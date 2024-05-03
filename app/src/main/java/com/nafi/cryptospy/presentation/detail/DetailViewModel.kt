package com.nafi.cryptospy.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nafi.cryptospy.data.model.Detail
import com.nafi.cryptospy.data.repository.DetailRepository
import com.nafi.cryptospy.data.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers

class DetailViewModel(
    private val extras: Bundle?,
    private val detailRepository: DetailRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    val idExtras = extras?.getString(DetailActivity.EXTRAS_ITEM)

    fun getDetailData(id: String) = detailRepository.getDetail(id).asLiveData(Dispatchers.IO)

    fun goToWeb(
        context: Context,
        url: String,
    ) {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.coingecko.com/en/coins/$url"),
            )
        context.startActivity(intent)
    }

    fun addToFavorite(detail: Detail) = favoriteRepository.addFavorite(detail).asLiveData(Dispatchers.IO)

    fun checkCoinFavorite(coinId: String) = favoriteRepository.checkFavoriteById(coinId).asLiveData(Dispatchers.IO)

    fun removeFromFavorite(coinId: String) = favoriteRepository.removeFavorite(coinId).asLiveData(Dispatchers.IO)
}
