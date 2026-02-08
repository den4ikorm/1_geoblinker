package org.example.geoblinker.presentation.features.geofences.edit

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class GeofenceEditState(
    val id: String? = null,
    val name: String = "",
    val radius: Float = 500f,
    val isCircle: Boolean = true,
    val centerLat: Double = 0.0,
    val centerLng: Double = 0.0,
    val isSaving: Boolean = false
) : ViewState

sealed interface GeofenceEditEvent : ViewEvent {
    data class OnNameChanged(val name: String) : GeofenceEditEvent
    data class OnRadiusChanged(val radius: Float) : GeofenceEditEvent
    data class OnTypeChanged(val isCircle: Boolean) : GeofenceEditEvent
    data object OnSave : GeofenceEditEvent
    data object OnBack : GeofenceEditEvent
}

sealed interface GeofenceEditEffect : ViewEffect {
    data object NavigateBack : Geof
# 1. CONTRACT (Параметры редактирования)
cat <<'EOF' > $HOME/WORKING_PROJECT/presentation/features/geofences/edit/GeofenceEditContract.kt
package org.example.geoblinker.presentation.features.geofences.edit

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class GeofenceEditState(
    val id: String? = null,
    val name: String = "",
    val radius: Float = 500f,
    val isCircle: Boolean = true,
    val centerLat: Double = 0.0,
    val centerLng: Double = 0.0,
    val isSaving: Boolean = false
) : ViewState

sealed interface GeofenceEditEvent : ViewEvent {
    data class OnNameChanged(val name: String) : GeofenceEditEvent
    data class OnRadiusChanged(val radius: Float) : GeofenceEditEvent
    data class OnTypeChanged(val isCircle: Boolean) : GeofenceEditEvent
    data object OnSave : GeofenceEditEvent
    data object OnBack : GeofenceEditEvent
}

sealed interface GeofenceEditEffect : ViewEffect {
    data object NavigateBack : GeofenceEditEffect
}
