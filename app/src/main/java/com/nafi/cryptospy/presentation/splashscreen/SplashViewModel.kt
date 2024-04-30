package com.nafi.cryptospy.presentation.splashscreen

import androidx.lifecycle.ViewModel
import com.nafi.cryptospy.data.repository.UserRepository

class SplashViewModel(private val repository: UserRepository) : ViewModel() {
    fun isUserLoggedIn() = repository.isLoggedIn()
}
