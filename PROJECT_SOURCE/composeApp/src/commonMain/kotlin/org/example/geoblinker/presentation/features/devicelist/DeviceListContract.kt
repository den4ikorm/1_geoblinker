package org.example.geoblinker.presentation.features.devicelist

data class DeviceItem(
    val id: String,
    val name: String,
    val statusText: String,    // "В сети", "Оффлайн"
    val statusColor: Long,     // HEX цвет
    val lastUpdate: String,    // Отформатированная дата
    val imei: String
)

data class DeviceListState(
    val devices: List<DeviceItem> = emptyList(),
    val isLoading: Long = 0,
    val searchQuery: String = ""
)

sealed class DeviceListEvent {
    object Refresh : DeviceListEvent()
    data class OnSearchChanged(val query: String) : DeviceListEvent()
    data class OnDeviceClicked(val id: String) : DeviceListEvent()
}
