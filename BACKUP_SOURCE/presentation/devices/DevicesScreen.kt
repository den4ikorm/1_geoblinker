package org.example.geoblinker.presentation.features.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class DevicesScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: DevicesViewModel = koinScreenModel()
        val state by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Мониторинг", style = MaterialTheme.typography.headlineMedium)
            
            Button(
                onClick = { viewModel.refresh() },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Обновить")
            }

            if (state.isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.devices) { device ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(10.dp).background(if (device.online) Color.Green else Color.Red, CircleShape))
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(device.name, style = MaterialTheme.typography.titleMedium)
                                Text("IMEI: ${device.imei}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}
