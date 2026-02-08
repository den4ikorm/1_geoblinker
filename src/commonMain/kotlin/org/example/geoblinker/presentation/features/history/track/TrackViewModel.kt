package org.example.geoblinker.presentation.features.history.track

import org.example.geoblinker.core.base.BaseViewModel
import kotlinx.coroutines.launch

class TrackViewModel : BaseViewModel<TrackState, TrackEvent, TrackEffect>(TrackState()) {
    override fun onEvent(event: TrackEvent) {
        when (event) {
            is TrackEvent.OnLoadTrack -> loadTrackData(event.tripId)
            is TrackEvent.OnBackClick -> setEffect { TrackEffect.NavigateBack }
        }
    }

    private fun loadTrackData(tripId: String) {
        updateState { copy(isLoading = true, tripId = tripId) }
        scope.launch {
            // Имитация загрузки точек маршрута (зигзаг для теста)
            val mockPoints = listOf(
                LatLngData(55.751244, 37.618423),
                LatLngData(55.755826, 37.617300),
                LatLngData(55.759123, 37.625411)
            )
            updateState { 
                copy(
                    points = mockPoints,
                    startPoint = mockPoints.first(),
                    endPoint = mockPoints.last(),
                    isLoading = false,
                    averageSpeed = "42 км/ч"
                ) 
            }
        }
    }
}
