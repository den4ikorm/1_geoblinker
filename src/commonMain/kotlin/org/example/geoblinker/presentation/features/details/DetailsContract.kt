package org.example.geoblinker.presentation.features.details

import org.example.geoblinker.presentation.core.*

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
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState
