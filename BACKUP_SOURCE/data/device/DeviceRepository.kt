package org.example.geoblinker.data.device

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable
import org.example.geoblinker.core.settings.SessionManager

@Serializable
data class DeviceResponse(
    val imei: String,
    val name: String,
    val model: String? = null,
    val online: Boolean = false
)

@Serializable
data class DeviceLocationResponse(
    val imei: String,
    val lat: Double,
    val lon: Double,
    val speed: Double? = null,
    val course: Double? = null,
    val timestamp: Long? = null
)

interface DeviceRepository {
    suspend fun getDeviceList(): List<DeviceResponse>
    suspend fun getDeviceLocation(imei: String): DeviceLocationResponse
}

class DeviceRepositoryImpl(
    private val client: HttpClient,
    private val sessionManager: SessionManager
) : DeviceRepository {

    private companion object {
        const val BASE_URL = "https://gps666.net"
    }

    override suspend fun getDeviceList(): List<DeviceResponse> {
        val sid = sessionManager.getSid() ?: ""
        return client.get("$BASE_URL/api/devices") {
            parameter("sid", sid)
        }.body()
    }

    override suspend fun getDeviceLocation(imei: String): DeviceLocationResponse {
        val sid = sessionManager.getSid() ?: ""
        return client.get("$BASE_URL/api/device/location") {
            parameter("sid", sid)
            parameter("imei", imei)
        }.body()
    }
}
