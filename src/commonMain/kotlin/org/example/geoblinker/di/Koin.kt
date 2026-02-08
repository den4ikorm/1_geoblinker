package org.example.geoblinker.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule())
    }

// Для iOS
fun initKoin() = initKoin {}

fun commonModule() = org.koin.dsl.module {
    // Здесь позже пропишем все репозитории и вьюмодели
}
