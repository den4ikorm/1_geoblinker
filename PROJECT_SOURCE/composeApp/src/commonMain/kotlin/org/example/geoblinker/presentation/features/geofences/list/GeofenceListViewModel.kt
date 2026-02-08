package org.example.geoblinker

import org.example.geoblinker

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
        updateState { copy(isLoading = 1L) }
        val mock = listOf(
            GeofenceItem("1", "Дом", "Круг", 500, true),
            GeofenceItem("2", "Работа", "Круг", 300, true)
        )
        updateState { copy(geofences = mock, isLoading = 0L) }
    }
}
