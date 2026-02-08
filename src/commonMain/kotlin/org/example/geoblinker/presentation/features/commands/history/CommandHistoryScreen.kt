package org.example.geoblinker.presentation.features.commands.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class CommandHistoryScreen : Screen {
    @Composable
    override fun Content() {
        val mockLogs = listOf(
            CommandLogItem("1", "Блокировка двигателя", "Сегодня, 14:20", "Выполнено", "Вы"),
            CommandLogItem("2", "Запрос координат", "Вчера, 23:10", "Выполнено", "Вы"),
            CommandLogItem("3", "Перезагрузка", "02.02.2026", "Ошибка: Нет сети", "Система")
        )

        Scaffold(
            topBar = { TopAppBar(title = { Text("Журнал команд") }) }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(mockLogs) { log ->
                    ListItem(
                        headlineContent = { Text(log.commandName) },
                        supportingContent = { Text("${log.dateTime} • ${log.initiator}") },
                        trailingContent = { 
                            Text(
                                log.status, 
                                color = if (log.status == "Выполнено") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            ) 
                        }
                    )
                    Divider()
                }
            }
        }
    }
}
