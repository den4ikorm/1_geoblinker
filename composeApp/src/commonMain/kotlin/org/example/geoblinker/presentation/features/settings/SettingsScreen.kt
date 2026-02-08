package org.example.geoblinker.presentation.features.settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.geoblinker.presentation.features.auth.LoginScreen

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(backgroundColor = Color(0xFF050A18)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("НАСТРОЙКИ", color = Color.White, style = MaterialTheme.typography.h5)
                Spacer(Modifier.height(20.dp))
                Button(onClick = { navigator.replaceAll(LoginScreen()) }) {
                    Text("ВЫЙТИ")
                }
            }
        }
    }
}
