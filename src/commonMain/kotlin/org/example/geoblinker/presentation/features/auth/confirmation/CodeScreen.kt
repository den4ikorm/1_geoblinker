package org.example.geoblinker.presentation.features.auth.confirmation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.geoblinker.presentation.features.common.components.BlinkerButton
import org.example.geoblinker.presentation.features.common.theme.GeoBlinkerTheme
import org.koin.compose.koinInject

class CodeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: CodeViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        GeoBlinkerTheme {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Введите код", style = MaterialTheme.typography.headlineMedium)
                Text("Мы отправили его в SMS", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
                
                Spacer(modifier = Modifier.height(32.dp))
                
                TextField(
                    value = state.code,
                    onValueChange = { viewModel.onEvent(CodeEvent.OnCodeChanged(it)) },
                    label = { Text("Код из SMS") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                BlinkerButton(
                    text = "Подтвердить",
                    onClick = { viewModel.onEvent(CodeEvent.OnVerifyClicked) },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isLoading
                )
            }
        }
    }
}
