package org.example.geoblinker.presentation.features.history.track

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TripDetailsView(state: TripDetailsState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Детали поездки", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        
        Card {
            Column(modifier = Modifier.padding(12.dp)) {
                DetailRow("Старт", state.startAddress)
                Divider(modifier = Modifier.padding(vertical = 4.dp))
                DetailRow("Финиш", state.endAddress)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Сетка параметров
        val items = listOf(
            TripStatItem("Пробег", "12.4 км", "📏"),
            TripStatItem("Макс. скор.", "85 км/ч", "🚀"),
            TripStatItem("В движении", "01:20", "⏱️"),
            TripStatItem("Стоянки", "15 мин", "🅿️")
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(160.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(item.icon)
                        Text(item.label, style = MaterialTheme.typography.labelSmall)
                        Text(item.value, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(label, modifier = Modifier.width(60.dp), style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}
