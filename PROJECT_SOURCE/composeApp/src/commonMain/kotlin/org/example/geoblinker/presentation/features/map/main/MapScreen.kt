package org.example.geoblinker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.example.geoblinker

class MapScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: MapViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        Scaffold { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                // Используем наш actual компонент карты
                // Передаем список устройств для отрисовки маркеров
                BlinkerMap(
                    modifier = Modifier.fillMaxSize(),
                    devices = state.devices, // Передаем ВСЮ модель Devices
                    onMarkerClick = { deviceId ->
                        // Логика перехода на детали при клике
                    }
                )
            }
        }
    }
}
