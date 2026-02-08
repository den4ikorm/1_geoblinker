package org.example.geoblinker.presentation.features.geofences.edit

import org.example.geoblinker.core.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class GeofenceEditViewModel : BaseViewModel<GeofenceEditState, GeofenceEditEvent, GeofenceEditEffect>(GeofenceEditState()) {
    override fun onEvent(event: GeofenceEditEvent) {
        when (event) {
            is GeofenceEditEvent.OnNameChanged -> updateState { copy(name = event.name) }
            is GeofenceEditEvent.OnRadiusChanged -> updateState { copy(radius = event.radius) }
            is GeofenceEditEvent.OnTypeChanged -> updateState { copy(isCircle = event.isCircle) }
            is GeofenceEditEvent.OnBack -> setEffect { GeofenceEditEffect.NavigateBack }
            is GeofenceEditEvent.OnSave -> saveGeofence()
        }
    }

    private fun saveGeofence() {
        updateState { copy(isSaving = true) }
        scope.launch {
            delay(1000) // Имитация записи в БД
            setEffect { GeofenceEditEffect.NavigateBack }
        }
    }
}
