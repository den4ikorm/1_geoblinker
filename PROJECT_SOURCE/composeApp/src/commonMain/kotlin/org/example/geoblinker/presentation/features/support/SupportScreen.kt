package org.example.geoblinker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class SupportScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Техподдержка") }) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Контент экрана Техподдержка появится здесь.")
            }
        }
    }
}
