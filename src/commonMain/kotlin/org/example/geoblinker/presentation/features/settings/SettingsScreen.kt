package org.example.geoblinker.presentation.features.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(state: SettingsState, onEvent: (SettingsEvent) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Настройки") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Переключатель уведомлений
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Уведомления")
                Switch(
                    checked = state.isNotificationsEnabled,
                    onCheckedChange = { onEvent(SettingsEvent.OnToggleNotifications) }
                )
            }

            Divider()

            // Выбор единиц (просто клик, логика в VM)
            TextButton(onClick = { onEvent(SettingsEvent.OnChangeUnitsClick) }) {
                Column {
                    Text("Единицы измерения", style = MaterialTheme.typography.body1)
                    Text(state.distanceUnit, style = MaterialTheme.typography.caption)
                }
            }

            Divider()

            // Очистка кеша
            Button(
                onClick = { onEvent(SettingsEvent.OnClearCacheClick) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text("ОЧИСТИТЬ КЕШ")
            }
        }
    }
}
