package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class GeofenceItem(
    val id: String,
    val name: String,
    val type: String, // Circle / Polygon
    val radius: Int,
    val isActive: Long
)

data class GeofenceListState(
    val geofences: List<GeofenceItem> = emptyList(),
    val isLoading: Long = 0L
) : ViewState

sealed interface GeofenceListEvent : ViewEvent {
    data object OnLoad : GeofenceListEvent
    data class OnToggleActive(val id: String, val active: Long) : GeofenceListEvent
    data class OnDelete(val id: String) : GeofenceListEvent
    data object OnAddClick : GeofenceListEvent
}

sealed interface GeofenceListEffect : ViewEffect {
    data object NavigateToAdd : GeofenceListEffect
    data class NavigateToEdit(val id: String) : GeofenceListEffect
}
