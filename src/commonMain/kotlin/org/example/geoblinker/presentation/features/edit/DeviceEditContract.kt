package org.example.geoblinker.presentation.features.edit

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class DeviceEditState(
    val imei: String = "",
    val deviceName: String = "",
    val selectedIconType: Int = 1,
    val isNameValid: Boolean = false,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isSaveSuccess: Boolean = false
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
