package org.example.geoblinker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

data class TrackScreen(val tripId: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: TrackViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(tripId) {
            viewModel.onEvent(TrackEvent.OnLoadTrack(tripId))
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Маршрут поездки") },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.onEvent(TrackEvent.OnBackClick) }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding).fillMaxSize()) {
                // Здесь будет Google Maps / MapLibre
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Карта с треком (Points: ${state.points.size})")
                }
                
                // Overlay со статистикой внизу
                Card(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp).fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Средняя скорость: ${state.averageSpeed}", style = MaterialTheme.typography.bodyLarge)
                        Text("Точек в пути: ${state.points.size}")
                    }
                }
            }
        }
    }
}
