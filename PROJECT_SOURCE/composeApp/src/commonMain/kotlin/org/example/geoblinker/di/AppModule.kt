package org.example.geoblinker.di

import org.koin.dsl.module
import org.example.geoblinker.core.network.HttpClientFactory
import org.example.geoblinker.domain.repository.AuthRepository
import org.example.geoblinker.domain.repository.DeviceRepository
import org.example.geoblinker.presentation.features.auth.AuthViewModel
import org.example.geoblinker.presentation.features.devicelist.DeviceListViewModel

val appModule = module {
    // Singletons (Сетевой клиент)
    single { HttpClientFactory.create() }
    
    // Репозитории
    single { AuthRepository(get()) }
    single { DeviceRepository(get()) }
    
    // ViewModels
    factory { AuthViewModel(get()) }
    factory { DeviceListViewModel(get()) }
}
