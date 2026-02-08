package org.example.geoblinker.presentation.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("GEOBLINKER БЕТОН", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = state.loginText,
            onValueChange = { viewModel.onEvent(AuthEvent.OnLoginChanged(it)) },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.passwordText,
            onValueChange = { viewModel.onEvent(AuthEvent.OnPasswordChanged(it)) },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading == 1L) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.onEvent(AuthEvent.OnLoginClicked) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ВОЙТИ")
            }
        }

        if (state.errorMessage.isNotEmpty()) {
            Text(state.errorMessage, color = MaterialTheme.colors.error)
        }
    }
}
