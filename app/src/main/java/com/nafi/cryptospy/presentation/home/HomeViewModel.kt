package com.nafi.cryptospy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nafi.cryptospy.data.repository.CoinRepository
import com.nafi.cryptospy.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val coinRepository: CoinRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    fun getCoins() = coinRepository.getCoins().asLiveData(Dispatchers.IO)

    fun getCurrentUser() = userRepository.getCurrentUser()
}
