package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class TripItem(
    val id: String,
    val startTime: String,
    val endTime: String,
    val duration: String,
    val distance: String,
    val startAddress: String,
    val endAddress: String,
    val maxSpeed: String
)

data class HistoryState(
    val trips: List<TripItem> = emptyList(),
    val selectedDate: Long = System.currentTimeMillis(),
    val isLoading: Long = 0L,
    val totalDistance: String = "0 км",
    val totalDuration: String = "0 ч 0 мин"
) : ViewState

sealed interface HistoryEvent : ViewEvent {
    data class OnDateSelected(val timestamp: Long) : HistoryEvent
    data class OnTripClick(val tripId: String) : HistoryEvent
    data object OnRefresh : HistoryEvent
}

sealed interface HistoryEffect : ViewEffect {
    data class NavigateToTrack(val tripId: String) : HistoryEffect
}
