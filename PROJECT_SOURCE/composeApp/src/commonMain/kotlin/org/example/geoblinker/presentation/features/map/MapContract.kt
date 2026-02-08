package org.example.geoblinker.presentation.features.map

data class MapMarker(
    val id: String,
    val latitude: Double,  // Double допустим для координат внутри модели данных
    val longitude: Double,
    val title: String,
    val iconType: String   // "car", "washer", etc.
)

data class MapState(
    val markers: List<MapMarker> = emptyList(),
    val centerLat: Double = 0.0,
    val centerLng: Double = 0.0,
    val zoomLevel: Long = 10,
    val selectedDeviceId: String = ""
)

sealed class MapEvent {
    data class OnMarkerClicked(val id: String) : MapEvent()
    data class OnZoomChanged(val level: Long) : MapEvent()
    object CenterOnMe : MapEvent()
}
