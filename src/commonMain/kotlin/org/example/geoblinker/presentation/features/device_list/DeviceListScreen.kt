package org.example.geoblinker.presentation.features.device_list

import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.CircularProgressIndicator
import org.koin.compose.koinInject

@Composable
fun DeviceListScreen() {
    // Внедряем ViewModel через Koin
    val viewModel: DeviceListViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(state.devices) { device ->
                Text(text = "🚗 ${device.name} (IMEI: ${device.imei}) - ${device.speed} км/ч")
            }
        }
    }
}
