package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker

data class GeofenceMapState(
    val centerLat: Double = 55.751244,
    val centerLng: Double = 37.618423,
    val radius: Float = 500f,
    val isLocked: Long = 0L
) : ViewState

sealed interface GeofenceMapEvent : ViewEvent {
    data class OnMapClick(val lat: Double, val lng: Double) : GeofenceMapEvent
    data class OnCameraMove(val lat: Double, val lng: Double) : GeofenceMapEvent
}
