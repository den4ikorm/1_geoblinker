package org.example.geoblinker.presentation.features.history.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun PlayerControlPanel() {
    val viewModel: PlayerViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { viewModel.onEvent(PlayerEvent.OnTogglePlay) }) {
                    Icon(if (state.isPlaying) Icons.Default.Close else Icons.Default.PlayArrow, null)
                }
                
                Slider(
                    value = state.currentProgress,
                    onValueChange = { viewModel.onEvent(PlayerEvent.OnSeek(it)) },
                    modifier = Modifier.weight(1f)
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(state.currentTimeText)
                TextButton(onClick = { 
                    val nextSpeed = when(state.playbackSpeed) { 1 -> 2; 2 -> 4; 4 -> 8; else -> 1 }
                    viewModel.onEvent(PlayerEvent.OnSpeedChange(nextSpeed))
                }) {
                    Text("${state.playbackSpeed}x")
                }
            }
        }
    }
}
