package com.nafi.cryptospy.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nafi.cryptospy.data.model.Favorite
import com.nafi.cryptospy.data.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun getAllFavorite() = favoriteRepository.getAllFavorite().asLiveData(Dispatchers.IO)

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(favorite).collect()
        }
    }
}
