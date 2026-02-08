package org.example.geoblinker.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val name: String,
    val imei: String,
    val lat: Long,
    val lon: Long,
    val speed: Int,
    val lastConnect: String,
    val isOnline: Boolean = false
) {
    // Авто-расчет координат для карты
    val displayLat: Double get() = lat.toDouble() / 1_000_000.0
    val displayLon: Double get() = lon.toDouble() / 1_000_000.0
}
