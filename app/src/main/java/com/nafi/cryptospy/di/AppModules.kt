package com.nafi.cryptospy.di

import com.google.firebase.auth.FirebaseAuth
import com.nafi.cryptospy.data.repository.UserRepository
import com.nafi.cryptospy.data.repository.UserRepositoryImpl
import com.nafi.cryptospy.data.source.firebase.FirebaseAuthDataSource
import com.nafi.cryptospy.data.source.firebase.FirebaseAuthDataSourceImpl
import com.nafi.cryptospy.presentation.login.LoginViewModel
import com.nafi.cryptospy.presentation.register.RegisterViewModel
import com.nafi.cryptospy.presentation.splashscreen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    private val networkModule =
        module {
        }

    private val firebaseModule =
        module {
            single<FirebaseAuth> { FirebaseAuth.getInstance() }
        }

    private val localModule =
        module {
        }

    private val datasource =
        module {
            single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
        }

    private val repository =
        module {
            single<UserRepository> { UserRepositoryImpl(get()) }
        }

    private val viewModel =
        module {
            viewModel {
                SplashViewModel(get())
            }
            viewModel {
                RegisterViewModel(get())
            }

            viewModel {
                LoginViewModel(get())
            }
        }

    val modules = listOf<Module>(networkModule, firebaseModule, localModule, datasource, repository, viewModel)
}
