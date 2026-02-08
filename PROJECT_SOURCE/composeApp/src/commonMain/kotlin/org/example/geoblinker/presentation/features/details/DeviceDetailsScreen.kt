package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.geoblinker
import org.example.geoblinker

class DeviceDetailsScreen(private val device: Devices) : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(device.name) }) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Технические данные", style = MaterialTheme.typography.titleMedium)
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                
                DetailRow("Госномер", device.registrationPlate)
                DetailRow("Напряжение", "${device.powerRate.toDouble() / 1000} V") 
                DetailRow("Скорость", "${device.speed} км/ч")
                DetailRow("Последняя связь", TimeUtils.formatTimestamp(device.bindingTime))
                DetailRow("Модель блока", device.modelName)
                DetailRow("IMEI", device.imei)

                if (device.breakdownForecast != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Прогноз: ${device.breakdownForecast}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.outline)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}
