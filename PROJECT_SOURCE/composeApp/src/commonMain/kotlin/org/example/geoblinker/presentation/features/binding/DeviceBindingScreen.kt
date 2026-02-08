package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class DeviceBindingScreen : Screen {
    @Composable
    override fun Content() {
        var imei by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }

        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Добавить трекер") }) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(24.dp).fillMaxSize()) {
                OutlinedTextField(
                    value = imei,
                    onValueChange = { imei = it },
                    label = { Text("Введите IMEI устройства") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Название (например, Лада)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { /* Save logic */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Подключить устройство")
                }
            }
        }
    }
}
