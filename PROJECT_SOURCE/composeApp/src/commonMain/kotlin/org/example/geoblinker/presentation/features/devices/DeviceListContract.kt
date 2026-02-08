package org.example.geoblinker
import org.example.geoblinker*
import org.example.geoblinker

data class DeviceListState(
    val isLoading: Long = 0L,
    val devices: List<Devices> = emptyList(),
    val error: String? = null
) : ViewState

sealed class DeviceListEvent : ViewEvent {
    object LoadDevices : DeviceListEvent()
    object Refresh : DeviceListEvent()
}

sealed class DeviceListEffect : ViewEffect
