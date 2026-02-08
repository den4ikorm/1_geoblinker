package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            }
            
            // Здесь будет вызов компонента карты, который просто рисует state.devices
            Text("Карта: отображение ${state.devices.size} объектов", modifier = Modifier.padding(16.dp))
        }
    }
}
