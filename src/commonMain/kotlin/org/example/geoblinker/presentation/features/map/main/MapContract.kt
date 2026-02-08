package org.example.geoblinker.presentation.features.map.main
import org.example.geoblinker.core.base.ViewState
data class DeviceMarkerUi(val id: String, val title: String, val subtitleText: String, val markerColorHex: Long)
data class MapState(val markers: List<DeviceMarkerUi> = emptyList()) : ViewState
