package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinViewModel

class DeviceListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<DeviceListViewModel>()
        val state by viewModel.viewState.collectAsState()

        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Мои устройства") }) }
        ) { padding ->
            if (state.isLoading) {
                Box(Modifier.fillMaxSize()) { CircularProgressIndicator() }
            } else {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(state.devices) { device ->
                        ListItem(
                            headlineContent = { Text(device.name) },
                            supportingContent = { Text("${device.registrationPlate} • ${device.powerRate.toDouble()/1000}V") },
                            trailingContent = { Text(if(device.speed > 0) "${device.speed} км/ч" else "Стоит") }
                        )
                    }
                }
            }
        }
    }
}
