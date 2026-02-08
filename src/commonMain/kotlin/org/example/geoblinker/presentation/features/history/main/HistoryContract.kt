package org.example.geoblinker.presentation.features.history.main

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

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
    val isLoading: Boolean = false,
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
