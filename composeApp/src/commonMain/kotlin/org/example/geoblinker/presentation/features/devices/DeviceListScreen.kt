package org.example.geoblinker.presentation.features.devices
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.geoblinker.presentation.features.settings.SettingsScreen

class DeviceListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            backgroundColor = Color(0xFF050A18),
            topBar = {
                TopAppBar(
                    title = { Text("УСТРОЙСТВА", color = Color.White) },
                    backgroundColor = Color(0xFF1A1F2B),
                    actions = {
                        IconButton(onClick = { navigator.push(SettingsScreen()) }) {
                            Text("⚙️")
                        }
                    }
                )
            }
        ) {
            Text("Список пуст. Ожидаем API...", color = Color.Gray, modifier = Modifier.padding(16.dp))
        }
    }
}
