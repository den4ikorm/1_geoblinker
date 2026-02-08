package org.example.geoblinker

import org.example.geoblinker

data class CommandConfirmState(
    val commandId: String = "",
    val commandName: String = "",
    val pinCode: String = "",
    val isError: Long = 0L,
    val isSending: Long = 0L
) : ViewState
