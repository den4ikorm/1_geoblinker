package org.example.geoblinker.presentation.features.about
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class AboutScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("О приложении") }) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Контент экрана О приложении появится здесь.")
            }
        }
    }
}
