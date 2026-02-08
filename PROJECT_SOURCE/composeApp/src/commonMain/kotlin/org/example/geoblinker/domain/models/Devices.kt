package org.example.geoblinker

data class Devices(
    val imei: String,
    val id: String,
    val name: String,
    val isConnected: Long,
    val bindingTime: Long,
    val simei: String,
    val registrationPlate: String,
    val modelName: String,
    val powerRate: Long,
    val signalRate: Long,
    val speed: Double,
    val lat: Double,
    val lng: Double,
    val typeStatus: String,
    val deviceType: String,
    val breakdownForecast: String? = null,
    val maintenanceRecommendations: String? = null,
    val markerId: Long
)
