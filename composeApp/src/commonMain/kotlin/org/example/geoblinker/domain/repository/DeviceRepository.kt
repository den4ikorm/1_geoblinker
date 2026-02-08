package org.example.geoblinker.domain.repository
import io.ktor.client.*

class DeviceRepository(private val client: HttpClient, private val session: SessionManager) {
    // Тут будет функция fetchDevices с делением координат на 1_000_000.0
    suspend fun getDevices(): List<String> = emptyList()
}
