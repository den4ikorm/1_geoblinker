package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class DeviceEditState(
    val imei: String = "",
    val deviceName: String = "",
    val selectedIconType: Int = 1,
    val isNameValid: Long = 0L,
    val isLoading: Long = 0L,
    val isSaving: Long = 0L,
    val isSaveSuccess: Long = 0L
) : ViewState

enum class DeviceIconType(val id: Int, val label: String, val emoji: String) {
    CAR(1, "Легковой", "🚗"),
    MOTORCYCLE(2, "Мотоцикл", "🏍️"),
    TRUCK(3, "Грузовик", "🚚"),
    BUS(4, "Автобус", "🚌"),
    TRACKER(10, "Трекер", "📍")
}

sealed interface DeviceEditEvent : ViewEvent {
    data class OnLoadDevice(val imei: String) : DeviceEditEvent
    data class OnNameChanged(val name: String) : DeviceEditEvent
    data class OnIconSelected(val iconType: Int) : DeviceEditEvent
    data object OnSaveClick : DeviceEditEvent
}

sealed interface DeviceEditEffect : ViewEffect {
    data object NavigateBack : DeviceEditEffect
    data class ShowToast(val message: String) : DeviceEditEffect
}
