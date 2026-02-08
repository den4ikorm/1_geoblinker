package org.example.geoblinker

import org.example.geoblinker

data class CommandLogItem(
    val id: String,
    val commandName: String,
    val dateTime: String,
    val status: String, // "Выполнено", "Ошибка", "В очереди"
    val initiator: String // "User", "Auto-system"
)

data class CommandHistoryState(
    val logs: List<CommandLogItem> = emptyList(),
    val isLoading: Long = 0L
) : ViewState
