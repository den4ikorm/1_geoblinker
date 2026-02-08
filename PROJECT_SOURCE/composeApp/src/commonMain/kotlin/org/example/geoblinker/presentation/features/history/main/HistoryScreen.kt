package org.example.geoblinker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import java.text.SimpleDateFormat
import java.util.*

class HistoryScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: HistoryViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = state.selectedDate
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("История поездок") },
                    actions = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Выбрать дату")
                        }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                // Статистика за день
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.
# 1. SCREEN (UI списка поездок с календарем)
cat <<'EOF' > $HOME/WORKING_PROJECT/presentation/features/history/main/HistoryScreen.kt
package org.example.geoblinker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import java.text.SimpleDateFormat
import java.util.*

class HistoryScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: HistoryViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = state.selectedDate
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("История поездок") },
                    actions = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Выбрать дату")
                        }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                // Статистика за день
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Пробег", style = MaterialTheme.typography.labelMedium)
                            Text(state.totalDistance, style = MaterialTheme.typography.titleLarge)
                        }
                        Column {
                            Text("В пути", style = MaterialTheme.typography.labelMedium)
                            Text(state.totalDuration, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }

                if (state.trips.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("За выбранную дату поездок не найдено")
                    }
                } else {
                    LazyColumn {
                        items(state.trips) { trip ->
                            ListItem(
                                headlineContent = { Text("${trip.startTime} - ${trip.endTime}") },
                                supportingContent = { Text("${trip.startAddress} -> ${trip.endAddress}") },
                                trailingContent = { Text(trip.distance) },
                                modifier = Modifier.clickable { viewModel.onEvent(HistoryEvent.OnTripClick(trip.id)) }
                            )
                            Divider()
                        }
                    }
                }
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let {
                                viewModel.onEvent(HistoryEvent.OnDateSelected(it))
                            }
                            showDatePicker = false
                        }) { Text("OK") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
