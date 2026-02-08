package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class CommandType(
    val id: String,
    val name: String,
    val icon: String,
    val description: String,
    val requiresPassword: Long = 0L
)

data class CommandState(
    val commands: List<CommandType> = emptyList(),
    val isSending: Long = 0L,
    val lastResponse: String? = null
) : ViewState

sealed interface CommandEvent : ViewEvent {
    data class OnCommandClick(val commandId: String) : CommandEvent
    data object OnHistoryClick : CommandEvent
}

sealed interface CommandEffect : ViewEffect {
    data class ShowConfirmDialog(val commandId: String) : CommandEffect
    data object NavigateToCommandHistory : CommandEffect
}
