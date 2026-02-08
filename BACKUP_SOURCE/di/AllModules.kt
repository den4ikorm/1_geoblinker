package org.example.geoblinker.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.geoblinker.core.settings.SessionManager
import org.example.geoblinker.domain.repository.AuthRepository
import org.example.geoblinker.data.device.DeviceRepository
import org.example.geoblinker.data.device.DeviceRepositoryImpl
import org.example.geoblinker.presentation.features.auth.AuthViewModel
import org.example.geoblinker.presentation.features.devices.DevicesViewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; isLenient = true })
            }
            install(Logging) { level = LogLevel.ALL }
        }
    }
    single { SessionManager() } // Убрали get(), так как конструктор пустой
}

val repositoryModule = module {
    single { AuthRepository(get()) }
    single<DeviceRepository> { DeviceRepositoryImpl(get(), get()) }
}

val screenModelModule = module {
    factory { AuthViewModel(get(), get()) }
    factory { DevicesViewModel(get()) }
}

val allModules = listOf(networkModule, repositoryModule, screenModelModule)
