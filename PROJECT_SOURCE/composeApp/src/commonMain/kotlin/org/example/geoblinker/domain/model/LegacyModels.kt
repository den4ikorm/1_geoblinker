package org.example.geoblinker.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CarModel(
    val id: Long,
    val name: String,
    val number: String,
    val lastLat: Long,
    val lastLng: Long,
    val speed: Long
)

@Serializable
data class ImeiDeviceModel(
    val imei: String,
    val deviceName: String,
    val modelType: String,
    val batteryLevel: Long,
    val posString: String // Тот самый вложенный JSON
)

@Serializable
data class TrackPoint(
    val lat: Long,
    val lng: Long,
    val time: Long,
    val speed: Long
)
