package org.example.geoblinker.presentation.features.commands.history

import org.example.geoblinker.core.base.ViewState

data class CommandLogItem(
    val id: String,
    val commandName: String,
    val dateTime: String,
    val status: String, // "Выполнено", "Ошибка", "В очереди"
    val initiator: String // "User", "Auto-system"
)

data class CommandHistoryState(
    val logs: List<CommandLogItem> = emptyList(),
    val isLoading: Boolean = false
) : ViewState
