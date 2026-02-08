package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.koin.dsl.module

val dataModule = module {
    single<DeviceRepository> { DeviceRepositoryImpl() }
    factory { GetDevicesUseCase(get()) }
}
