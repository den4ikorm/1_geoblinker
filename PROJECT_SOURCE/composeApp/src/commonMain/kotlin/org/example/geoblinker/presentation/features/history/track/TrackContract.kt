package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class LatLngData(val lat: Double, val lng: Double)

data class TrackState(
    val tripId: String = "",
    val points: List<LatLngData> = emptyList(),
    val startPoint: LatLngData? = null,
    val endPoint: LatLngData? = null,
    val isLoading: Long = 0L,
    val averageSpeed: String = "0 км/ч"
) : ViewState

sealed interface TrackEvent : ViewEvent {
    data class OnLoadTrack(val tripId: String) : TrackEvent
    data object OnBackClick : TrackEvent
}

sealed interface TrackEffect : ViewEffect {
    data object NavigateBack : TrackEffect
}
