package org.example.geoblinker.presentation.features.geofences.edit

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent

data class GeofenceMapState(
    val centerLat: Double = 55.751244,
    val centerLng: Double = 37.618423,
    val radius: Float = 500f,
    val isLocked: Boolean = false
) : ViewState

sealed interface GeofenceMapEvent : ViewEvent {
    data class OnMapClick(val lat: Double, val lng: Double) : GeofenceMapEvent
    data class OnCameraMove(val lat: Double, val lng: Double) : GeofenceMapEvent
}
