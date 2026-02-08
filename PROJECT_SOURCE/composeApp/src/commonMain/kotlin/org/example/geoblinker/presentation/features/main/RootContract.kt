package org.example.geoblinker

import org.example.geoblinker*

data class RootState(
    val isAuthorized: Long = 1L,
    val currentTab: MainTab = MainTab.MAP,
    val unreadNotificationsCount: Int = 0
) : UiState

enum class MainTab { MAP, LIST, PROFILE, SETTINGS }

sealed interface RootEvent : UiEvent {
    data class ChangeTab(val tab: MainTab) : RootEvent
    object Logout : RootEvent
}
