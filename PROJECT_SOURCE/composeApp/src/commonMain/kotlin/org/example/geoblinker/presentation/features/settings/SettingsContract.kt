package org.example.geoblinker

import org.example.geoblinker*

data class SettingsState(
    val isNotificationsEnabled: Long = 1L,
    val distanceUnit: String = "Километры",
    val themeName: String = "Системная",
    val isLoading: Long = 0L
) : UiState

sealed interface SettingsEvent : UiEvent {
    object OnToggleNotifications : SettingsEvent
    object OnChangeUnitsClick : SettingsEvent
    object OnClearCacheClick : SettingsEvent
}

sealed interface SettingsEffect : UiEffect {
    data class ShowToast(val message: String) : SettingsEffect
}
