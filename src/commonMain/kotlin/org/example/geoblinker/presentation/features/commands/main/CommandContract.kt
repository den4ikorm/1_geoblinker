package org.example.geoblinker.presentation.features.commands.main

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class CommandType(
    val id: String,
    val name: String,
    val icon: String,
    val description: String,
    val requiresPassword: Boolean = false
)

data class CommandState(
    val commands: List<CommandType> = emptyList(),
    val isSending: Boolean = false,
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
