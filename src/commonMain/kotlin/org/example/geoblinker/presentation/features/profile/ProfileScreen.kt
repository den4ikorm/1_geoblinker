package org.example.geoblinker.presentation.features.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Мой профиль") }) }
    ) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.user?.let { user ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = user.userName, style = MaterialTheme.typography.h5)
                        Text(text = user.phone, style = MaterialTheme.typography.body1)
                    }
                }

                InfoRow("Дата регистрации", user.registrationDate)
                InfoRow("Версия приложения", user.appVersion)

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { onEvent(ProfileEvent.OnLogoutClick) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
                ) {
                    Text("ВЫЙТИ ИЗ АККАУНТА", color = MaterialTheme.colors.onError)
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.caption)
        Text(text = value, style = MaterialTheme.typography.body2)
    }
}
