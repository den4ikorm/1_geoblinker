package org.example.geoblinker

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun ConfirmationCodeScreen(phone: String) {
    val viewModel: PhoneViewModel = koinInject() // Используем общую Auth ViewModel
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Код отправлен на $phone")
        TextField(
            value = state.code,
            onValueChange = { viewModel.onEvent(AuthEvent.OnCodeInput(it)) },
            label = { Text("4-значный код") }
        )
        Button(
            onClick = { viewModel.onEvent(AuthEvent.OnSubmitCode) },
            enabled = state.code.length == 4
        ) {
            Text("ПОДТВЕРДИТЬ")
        }
    }
}
