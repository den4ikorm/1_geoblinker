package org.example.geoblinker.di
import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.russhwolf.settings.Settings
import org.example.geoblinker.domain.repository.*

val appModule = module {
    single { Settings() }
    single { SessionManager(get()) }
    single { 
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; isLenient = true })
            }
        }
    }
    single { AuthRepository(get(), get()) }
    single { DeviceRepository(get(), get()) }
}
