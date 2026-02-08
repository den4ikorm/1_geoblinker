package org.example.geoblinker.presentation.features.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.geoblinker.presentation.core.widgets.InfoCard

@Composable
fun DetailsScreen(state: DetailsState, onEvent: (DetailsEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.device?.name ?: "Загрузка...") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(DetailsEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        state.device?.let { ui ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Вся логика цвета и текста уже в ViewModel!
                InfoCard("Статус зажигания", ui.ignitionText, Color(ui.ignitionColor))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoCard("Скорость", ui.speedText, modifier = Modifier.weight(1f))
                    InfoCard("Топливо", ui.fuelText, modifier = Modifier.weight(1f))
                }

                InfoCard("Напряжение АКБ", ui.voltageText)
                InfoCard("Пробег за день", ui.mileageText)
                InfoCard("Последний адрес", ui.address)
                
                Text(
                    text = "Системный ID: ${ui.id}", 
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        state.error?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(16.dp))
        }
    }
}
