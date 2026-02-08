package org.example.geoblinker
import org.example.geoblinker
data class DeviceMarkerUi(val id: String, val title: String, val subtitleText: String, val markerColorHex: Long)
data class MapState(val markers: List<DeviceMarkerUi> = emptyList()) : ViewState
