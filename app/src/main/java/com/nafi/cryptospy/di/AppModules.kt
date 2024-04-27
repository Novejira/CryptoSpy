package com.nafi.cryptospy.di

import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    private val networkModule =
        module {
        }

    private val localModule =
        module {
        }

    private val datasource =
        module {
        }

    private val repository =
        module {
        }

    private val viewModel =
        module {
        }

    val modules = listOf<Module>(networkModule, localModule, datasource, repository, viewModel)
}
