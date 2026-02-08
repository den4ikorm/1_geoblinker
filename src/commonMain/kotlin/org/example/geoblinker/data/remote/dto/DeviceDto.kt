package org.example.geoblinker.data.remote.dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("imei") val imei: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("is_online") val isOnline: Boolean,
    @SerialName("last_update") val lastUpdate: Long
)
