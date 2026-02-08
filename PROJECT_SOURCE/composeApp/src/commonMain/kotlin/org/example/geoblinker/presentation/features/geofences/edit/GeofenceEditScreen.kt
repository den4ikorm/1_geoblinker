package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

data class GeofenceEditScreen(val geofenceId: String? = null) : Screen {
    @Composable
    override fun Content() {
        val viewModel: GeofenceEditViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(if (geofenceId == null) "Новая зона" else "Правка зоны") },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.onEvent(GeofenceEditEvent.OnBack) }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(GeofenceEditEvent.OnNameChanged(it)) },
                    label = { Text("Название (напр. Гараж)") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text("Радиус: ${state.radius.toInt()} м")
                Slider(
                    value = state.radius,
                    onValueChange = { viewModel.onEvent(GeofenceEditEvent.OnRadiusChanged(it)) },
                    valueRange = 100f..5000f,
                    steps = 49
                )

                Spacer(modifier = Modifier.height(24.dp))
                
                Row {
                    FilterChip(
                        selected = state.isCircle,
                        onClick = { viewModel.onEvent(GeofenceEditEvent.OnTypeChanged(true)) },
                        label = { Text("Круг") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterChip(
                        selected = !state.isCircle,
                        onClick = { viewModel.onEvent(GeofenceEditEvent.OnTypeChanged(false)) },
                        label = { Text("Полигон") }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = { viewModel.onEvent(GeofenceEditEvent.OnSave) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.name.isNotBlank() && !state.isSaving
                ) {
                    Text(if (state.isSaving) "Сохранение..." else "Сохранить зону")
                }
            }
        }
    }
}
