package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import kotlinx.coroutines.launch

data class MapState(
    val devices: List<org.example.geoblinker.domain.models.Devices> = emptyList(),
    val isLoading: Boolean = false
)

class MapViewModel(
    private val repository: DeviceRepository
) : BaseViewModel<MapState, Unit, Unit>(MapState()) {

    init { loadMarkers() }

    private fun loadMarkers() {
        updateState { copy(isLoading = 1L) }
        coroutineScope.launch {
            repository.getDevices().onSuccess { list ->
                updateState { copy(devices = list, isLoading = 0L) }
            }
        }
    }
}
