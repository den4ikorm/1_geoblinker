package org.example.geoblinker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

class CommandScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: CommandViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = { TopAppBar(title = { Text("Команды") }) }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(state.commands) { cmd ->
                    ListItem(
                        headlineContent = { Text(cmd.name) },
                        supportingContent = { Text(cmd.description) },
                        leadingContent = { Text(cmd.icon, style = MaterialTheme.typography.headlineSmall) },
                        modifier = Modifier.clickable { viewModel.onEvent(CommandEvent.OnCommandClick(cmd.id)) }
                    )
                    Divider()
                }
            }
        }
    }
}
