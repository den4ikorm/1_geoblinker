package org.example.geoblinker.presentation.features.geofences.list

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class GeofenceItem(
    val id: String,
    val name: String,
    val type: String, // Circle / Polygon
    val radius: Int,
    val isActive: Boolean
)

data class GeofenceListState(
    val geofences: List<GeofenceItem> = emptyList(),
    val isLoading: Boolean = false
) : ViewState

sealed interface GeofenceListEvent : ViewEvent {
    data object OnLoad : GeofenceListEvent
    data class OnToggleActive(val id: String, val active: Boolean) : GeofenceListEvent
    data class OnDelete(val id: String) : GeofenceListEvent
    data object OnAddClick : GeofenceListEvent
}

sealed interface GeofenceListEffect : ViewEffect {
    data object NavigateToAdd : GeofenceListEffect
    data class NavigateToEdit(val id: String) : GeofenceListEffect
}
