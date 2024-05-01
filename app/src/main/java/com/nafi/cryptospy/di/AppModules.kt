package com.nafi.cryptospy.di

import com.google.firebase.auth.FirebaseAuth
import com.nafi.cryptospy.data.datasource.DetailApiDataSource
import com.nafi.cryptospy.data.datasource.DetailDataSource
import com.nafi.cryptospy.data.datasource.coin.CoinApiDataSource
import com.nafi.cryptospy.data.datasource.coin.CoinDataSource
import com.nafi.cryptospy.data.repository.CoinRepository
import com.nafi.cryptospy.data.repository.CoinRepositoryImpl
import com.nafi.cryptospy.data.datasource.favorite.FavoriteDataSource
import com.nafi.cryptospy.data.datasource.favorite.FavoriteDataSourceImpl
import com.nafi.cryptospy.data.repository.DetailRepository
import com.nafi.cryptospy.data.repository.DetailRepositoryImpl
import com.nafi.cryptospy.data.repository.FavoriteRepository
import com.nafi.cryptospy.data.repository.FavoriteRepositoryImpl
import com.nafi.cryptospy.data.repository.UserRepository
import com.nafi.cryptospy.data.repository.UserRepositoryImpl
import com.nafi.cryptospy.data.source.firebase.FirebaseAuthDataSource
import com.nafi.cryptospy.data.source.firebase.FirebaseAuthDataSourceImpl
import com.nafi.cryptospy.data.source.local.AppDatabase
import com.nafi.cryptospy.data.source.local.dao.FavoriteDao
import com.nafi.cryptospy.data.source.network.service.CryptoSpyApiService
import com.nafi.cryptospy.presentation.detail.DetailViewModel
import com.nafi.cryptospy.presentation.favorite.FavoriteViewModel
import com.nafi.cryptospy.presentation.login.LoginViewModel
import com.nafi.cryptospy.presentation.profile.ProfileViewModel
import com.nafi.cryptospy.presentation.register.RegisterViewModel
import com.nafi.cryptospy.presentation.splashscreen.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    private val networkModule =
        module {
            single<CryptoSpyApiService> { CryptoSpyApiService.invoke() }
        }

    private val firebaseModule =
        module {
            single<FirebaseAuth> { FirebaseAuth.getInstance() }
        }

    private val localModule =
        module {
            single<AppDatabase> { AppDatabase.getInstance(androidContext()) }
            single<FavoriteDao> { get<AppDatabase>().FavoriteDao() }
        }

    private val datasource =
        module {
            single<CoinDataSource> { CoinApiDataSource(get()) }
            single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
            single<DetailDataSource> { DetailApiDataSource(get()) }
            single<FavoriteDataSource> { FavoriteDataSourceImpl(get()) }
            single<FavoriteDataSource>{ FavoriteDataSourceImpl(get()) }
        }

    private val repository =
        module {
            single<CoinRepository> { CoinRepositoryImpl(get()) }
            single<UserRepository> { UserRepositoryImpl(get()) }
            single<DetailRepository> { DetailRepositoryImpl(get()) }
            single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
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
            viewModel {
                ProfileViewModel(get())
            }
            viewModel { params ->
                DetailViewModel(
                    extras = params.get(),
                    detailRepository = get(),
                    favoriteRepository = get(),
                )
            }
            viewModel {
                FavoriteViewModel(get())
            }
        }

    val modules =
        listOf<Module>(
            networkModule,
            firebaseModule,
            localModule,
            datasource,
            repository,
            viewModel,
        )
}
