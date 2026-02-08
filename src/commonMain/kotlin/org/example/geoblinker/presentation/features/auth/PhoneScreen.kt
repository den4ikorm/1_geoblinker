package org.example.geoblinker.presentation.features.auth.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun PhoneScreen() {
    val viewModel: PhoneViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вход в систему", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.phoneNumber,
            onValueChange = { viewModel.onEvent(PhoneEvent.OnPhoneInput(it)) },
            label = { Text("Номер телефона") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            isError = state.error != null
        )

        if (state.error != null) {
            Text(text = state.error!!, color = MaterialTheme.colors.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.onEvent(PhoneEvent.OnSubmitClick) },
            enabled = state.isPhoneValid && !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(size = 20.dp, color = MaterialTheme.colors.onPrimary)
            } else {
                Text("ПОЛУЧИТЬ КОД")
            }
        }
    }
}
