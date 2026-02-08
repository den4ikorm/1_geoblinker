package org.example.geoblinker.presentation.features.notifications

import org.example.geoblinker.core.base.BaseViewModel

class NotificationsViewModel : BaseViewModel<NotificationsState, NotificationsEvent, Unit>(NotificationsState()) {
    init { onEvent(NotificationsEvent.OnLoad) }

    override fun onEvent(event: NotificationsEvent) {
        when (event) {
            NotificationsEvent.OnLoad -> {
                updateState { copy(items = listOf(
                    NotificationItem("1", "Двигатель запущен", "10:05", NotifType.INFO),
                    NotificationItem("2", "Выход из геозоны!", "09:42", NotifType.ALARM),
                    NotificationItem("3", "Низкий заряд батареи", "08:15", NotifType.STATUS)
                )) }
            }
            NotificationsEvent.OnClearAll -> updateState { copy(items = emptyList()) }
        }
    }
}
