package org.example.geoblinker.presentation.features.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

class NotificationsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NotificationsViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Уведомления") }) }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
                items(state.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text(item.time) },
                        leadingContent = {
                            val icon = if (item.type == NotifType.ALARM) Icons.Default.Warning else Icons.Default.Notifications
                            Icon(icon, contentDescription = null, tint = if (item.type == NotifType.ALARM) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
