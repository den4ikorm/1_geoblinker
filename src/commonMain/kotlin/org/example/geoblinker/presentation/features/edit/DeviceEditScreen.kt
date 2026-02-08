package org.example.geoblinker.presentation.features.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

data class DeviceEditScreen(val imei: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: DeviceEditViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(imei) { viewModel.onEvent(DeviceEditEvent.OnLoadDevice(imei)) }

        Scaffold(
            topBar = { TopAppBar(title = { Text("Настройки устройства") }) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                OutlinedTextField(
                    value = state.deviceName,
                    onValueChange = { viewModel.onEvent(DeviceEditEvent.OnNameChanged(it)) },
                    label = { Text("Название") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                Text("Выберите иконку:")
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    DeviceIconType.values().forEach { icon ->
                        Button(
                            onClick = { viewModel.onEvent(DeviceEditEvent.OnIconSelected(icon.id)) },
                            colors = if (state.selectedIconType == icon.id) 
                                ButtonDefaults.buttonColors() else ButtonDefaults.filledTonalButtonColors()
                        ) {
                            Text(icon.emoji)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = { viewModel.onEvent(DeviceEditEvent.OnSaveClick) },
                    enabled = state.isNameValid && !state.isSaving,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (state.isSaving) "Сохранение..." else "Сохранить")
                }
            }
        }
    }
}
