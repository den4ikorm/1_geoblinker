package org.example.geoblinker.presentation.features.device_list

import org.example.geoblinker.domain.model.Devices
import org.example.geoblinker.presentation.core.UiEvent
import org.example.geoblinker.presentation.core.UiState

data class DeviceListState(
    val devices: List<Devices> = emptyList(),
    val isLoading: Boolean = false
) : UiState

sealed interface DeviceListEvent : UiEvent {
    data object LoadDevices : DeviceListEvent
    data class OnDeviceClick(val imei: String) : DeviceListEvent
}
