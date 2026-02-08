package org.example.geoblinker.presentation.features.geofences.list

import org.example.geoblinker.core.base.BaseViewModel

class GeofenceListViewModel : BaseViewModel<GeofenceListState, GeofenceListEvent, GeofenceListEffect>(GeofenceListState()) {
    init { onEvent(GeofenceListEvent.OnLoad) }

    override fun onEvent(event: GeofenceListEvent) {
        when (event) {
            is GeofenceListEvent.OnLoad -> loadGeofences()
            is GeofenceListEvent.OnToggleActive -> {} // Логика вкл/выкл
            is GeofenceListEvent.OnDelete -> {} // Логика удаления
            is GeofenceListEvent.OnAddClick -> setEffect { GeofenceListEffect.NavigateToAdd }
        }
    }

    private fun loadGeofences() {
        updateState { copy(isLoading = true) }
        val mock = listOf(
            GeofenceItem("1", "Дом", "Круг", 500, true),
            GeofenceItem("2", "Работа", "Круг", 300, true)
        )
        updateState { copy(geofences = mock, isLoading = false) }
    }
}
