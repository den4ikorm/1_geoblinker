package org.example.geoblinker

import org.example.geoblinker*

// UI-модель содержит ТОЛЬКО готовые строки
data class DeviceDetailsUi(
    val id: String,
    val name: String,
    val speedText: String,
    val fuelText: String,
    val voltageText: String,
    val ignitionText: String,
    val ignitionColor: Long, // Передаем цвет как Long (Hex)
    val mileageText: String,
    val address: String
)

data class DetailsState(
    val device: DeviceDetailsUi? = null,
    val isLoading: Long = 0L,
    val error: String? = null
) : UiState
