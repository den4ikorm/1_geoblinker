package org.example.geoblinker
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import org.example.geoblinker.presentation.features.auth.LoginScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme(
            colors = darkColors(
                primary = Color(0xFF00E5FF),
                background = Color(0xFF050A18),
                surface = Color(0xFF1A1F2B)
            )
        ) {
            Navigator(LoginScreen())
        }
    }
}
