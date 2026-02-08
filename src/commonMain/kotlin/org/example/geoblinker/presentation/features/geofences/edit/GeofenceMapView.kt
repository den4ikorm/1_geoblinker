package org.example.geoblinker.presentation.features.geofences.edit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun GeofenceMapView(state: GeofenceMapState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.LightGray) // Заглушка под карту
    ) {
        // Рисуем индикатор геозоны поверх карты
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = size / 2f
            // Внешний круг зоны (полупрозрачный)
            drawCircle(
                color = Color.Blue.copy(alpha = 0.2f),
                radius = state.radius / 2, // Условный масштаб
                center = center
            )
            // Контур зоны
            drawCircle(
                color = Color.Blue,
                radius = state.radius / 2,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )
            // Центр зоны
            drawCircle(
                color = Color.Red,
                radius = 5.dp.toPx(),
                center = center
            )
        }
        
        Text(
            "Наведите центр карты на нужную точку",
            modifier = Modifier.align(Alignment.TopCenter).padding(8.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}
