package org.example.geoblinker.presentation.features.history.track

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class LatLngData(val lat: Double, val lng: Double)

data class TrackState(
    val tripId: String = "",
    val points: List<LatLngData> = emptyList(),
    val startPoint: LatLngData? = null,
    val endPoint: LatLngData? = null,
    val isLoading: Boolean = false,
    val averageSpeed: String = "0 км/ч"
) : ViewState

sealed interface TrackEvent : ViewEvent {
    data class OnLoadTrack(val tripId: String) : TrackEvent
    data object OnBackClick : TrackEvent
}

sealed interface TrackEffect : ViewEffect {
    data object NavigateBack : TrackEffect
}
