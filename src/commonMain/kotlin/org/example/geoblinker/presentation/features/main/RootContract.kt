package org.example.geoblinker.presentation.features.main

import org.example.geoblinker.presentation.core.*

data class RootState(
    val isAuthorized: Boolean = true,
    val currentTab: MainTab = MainTab.MAP,
    val unreadNotificationsCount: Int = 0
) : UiState

enum class MainTab { MAP, LIST, PROFILE, SETTINGS }

sealed interface RootEvent : UiEvent {
    data class ChangeTab(val tab: MainTab) : RootEvent
    object Logout : RootEvent
}
