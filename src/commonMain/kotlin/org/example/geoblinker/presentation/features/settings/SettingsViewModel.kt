package org.example.geoblinker.presentation.features.settings

import org.example.geoblinker.presentation.core.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsState, SettingsEvent, SettingsEffect>(SettingsState()) {
    override fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnToggleNotifications -> {
                updateState { it.copy(isNotificationsEnabled = !it.isNotificationsEnabled) }
            }
            SettingsEvent.OnChangeUnitsClick -> {
                sendEffect(SettingsEffect.ShowToast("Смена единиц будет доступна в Stage 7"))
            }
            SettingsEvent.OnClearCacheClick -> {
                sendEffect(SettingsEffect.ShowToast("Кеш очищен"))
            }
        }
    }
}
