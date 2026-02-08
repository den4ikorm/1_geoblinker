package org.example.geoblinker.presentation.features.settings

import org.example.geoblinker.presentation.core.*

data class SettingsState(
    val isNotificationsEnabled: Boolean = true,
    val distanceUnit: String = "Километры",
    val themeName: String = "Системная",
    val isLoading: Boolean = false
) : UiState

sealed interface SettingsEvent : UiEvent {
    object OnToggleNotifications : SettingsEvent
    object OnChangeUnitsClick : SettingsEvent
    object OnClearCacheClick : SettingsEvent
}

sealed interface SettingsEffect : UiEffect {
    data class ShowToast(val message: String) : SettingsEffect
}
