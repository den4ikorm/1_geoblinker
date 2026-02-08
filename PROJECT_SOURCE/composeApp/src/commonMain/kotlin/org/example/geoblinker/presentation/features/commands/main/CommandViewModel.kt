package org.example.geoblinker

import org.example.geoblinker

class CommandViewModel : BaseViewModel<CommandState, CommandEvent, CommandEffect>(CommandState()) {
    init {
        val list = listOf(
            CommandType("stop_engine", "Блокировка двигателя", "🚫", "Разомкнуть цепь зажигания", true),
            CommandType("resume_engine", "Разблокировка", "⚡", "Восстановить цепь зажигания", true),
            CommandType("reboot", "Перезагрузка", "🔄", "Программная перезагрузка трекера"),
            CommandType("get_pos", "Запрос координат", "📍", "Получить текущую позицию через SMS")
        )
        updateState { copy(commands = list) }
    }

    override fun onEvent(event: CommandEvent) {
        when (event) {
            is CommandEvent.OnCommandClick -> setEffect { CommandEffect.ShowConfirmDialog(event.commandId) }
            is CommandEvent.OnHistoryClick -> setEffect { CommandEffect.NavigateToCommandHistory }
        }
    }
}
