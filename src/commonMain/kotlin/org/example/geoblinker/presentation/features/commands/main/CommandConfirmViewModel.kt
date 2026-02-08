package org.example.geoblinker.presentation.features.commands.main

import org.example.geoblinker.core.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class CommandConfirmViewModel : BaseViewModel<CommandConfirmState, Any, Any>(CommandConfirmState()) {
    fun sendCommand(id: String, pin: String) {
        if (pin != "1234") { // Простейшая проверка для теста
            updateState { copy(isError = true) }
            return
        }
        
        updateState { copy(isSending = true, isError = false) }
        scope.launch {
            delay(2000) // Имитация работы сети
            updateState { copy(isSending = false) }
            // Здесь будет эффект закрытия диалога и уведомление об успехе
        }
    }
}
