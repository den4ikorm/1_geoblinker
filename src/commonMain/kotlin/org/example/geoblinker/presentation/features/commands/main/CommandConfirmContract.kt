package org.example.geoblinker.presentation.features.commands.main

import org.example.geoblinker.core.base.ViewState

data class CommandConfirmState(
    val commandId: String = "",
    val commandName: String = "",
    val pinCode: String = "",
    val isError: Boolean = false,
    val isSending: Boolean = false
) : ViewState
