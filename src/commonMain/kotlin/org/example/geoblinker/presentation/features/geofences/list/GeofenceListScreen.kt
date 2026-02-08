package org.example.geoblinker.presentation.features.geofences.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

class GeofenceListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: GeofenceListViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = { TopAppBar(title = { Text("Геозоны") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.onEvent(GeofenceListEvent.OnAddClick) }) {
                    Icon(Icons.Default.Add, null)
                }
            }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(state.geofences) { zone ->
                    ListItem(
                        headlineContent = { Text(zone.name) },
                        supportingContent = { Text("${zone.type}, радиус ${zone.radius}м") },
                        trailingContent = {
                            Row {
                                Switch(checked = zone.isActive, onCheckedChange = { 
                                    viewModel.onEvent(GeofenceListEvent.OnToggleActive(zone.id, it)) 
                                })
                                IconButton(onClick = { viewModel.onEvent(GeofenceListEvent.OnDelete(zone.id)) }) {
                                    Icon(Icons.Default.Delete, null)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
