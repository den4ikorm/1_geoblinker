package org.example.geoblinker

import org.example.geoblinker

data class TripStatItem(
    val label: String,
    val value: String,
    val icon: String
)

data class TripDetailsState(
    val stats: List<TripStatItem> = emptyList(),
    val startAddress: String = "ул. Ленина, 10",
    val endAddress: String = "пр. Мира, 45",
    val idleTime: String = "15 мин",
    val moveTime: String = "1 ч 20 мин"
) : ViewState
