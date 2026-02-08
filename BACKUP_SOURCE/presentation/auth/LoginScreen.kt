package org.example.geoblinker.presentation.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.geoblinker.presentation.features.devices.DevicesScreen

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinScreenModel<AuthViewModel>()

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("GeoBlinker 2.0", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(32.dp))

            TextField(
                value = viewModel.loginState.value,
                onValueChange = { viewModel.updateLogin(it) },
                label = { Text("Логин") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(Modifier.height(8.dp))

            TextField(
                value = viewModel.passState.value,
                onValueChange = { viewModel.updatePass(it) },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            viewModel.errorText.value?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            if (viewModel.isLoading.value) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        viewModel.onLoginClick {
                            navigator.push(DevicesScreen())
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Войти")
                }
            }
        }
    }
}
