package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.geoblinker

@Composable
fun ListScreen(state: ListState, onEvent: (ListEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Список устройств") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Поиск (событие прокидывается в VM)
            TextField(
                value = state.searchQuery,
                onValueChange = { onEvent(ListEvent.OnSearchQueryChanged(it)) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                placeholder = { Text("Поиск устройства...") }
            )

            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            LazyColumn {
                items(state.filteredDevices) { uiDevice ->
                    DeviceItem(
                        ui = uiDevice,
                        onClick = { onEvent(ListEvent.OnDeviceClick(uiDevice.id)) }
                    )
                }
            }
            
            state.error?.let {
                Text(it, color = MaterialTheme.colors.error, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
