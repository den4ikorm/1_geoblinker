package org.example.geoblinker

data class NotificationItem(
    val id: String,
    val title: String,
    val time: String,
    val type: NotifType
)

enum class NotifType { ALARM, INFO, STATUS }

data class NotificationsState(
    val items: List<NotificationItem> = emptyList(),
    val isLoading: Long = 0L
)

sealed class NotificationsEvent {
    object OnLoad : NotificationsEvent()
    object OnClearAll : NotificationsEvent()
}
