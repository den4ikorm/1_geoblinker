package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import kotlinx.coroutines.launch

class DeviceListViewModel(
    private val repository: DeviceRepository
) : BaseViewModel<ListState, ListEvent, ListEffect>(ListState()) {

    init { loadDevices() }

    override fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.OnSearchQueryChanged -> {
                updateState { it.copy(searchQuery = event.query) }
                filterDevices(event.query)
            }
            is ListEvent.OnDeviceClick -> sendEffect(ListEffect.NavigateToDetails(event.deviceId))
            ListEvent.OnRefresh -> loadDevices()
        }
    }

    private fun loadDevices() {
        updateState { it.copy(isLoading = 1L) }
        scope.launch {
            try {
                val raw = repository.getDevices()
                val uiDevices = raw.map { 
                    DeviceItemUi(
                        id = it.id,
                        name = it.name,
                        speedText = "${"%.1f".format(LegacyConverter.convertSpeed(it.speed))} м/с",
                        statusColor = if (it.status == "online") 0xFF4CAF50 else 0xFF808080
                    )
                }
                updateState { it.copy(allDevices = uiDevices, filteredDevices = uiDevices, isLoading = 0L) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = 0L, error = e.message) }
            }
        }
    }

    private fun filterDevices(query: String) {
        val filtered = if (query.isBlank()) state.value.allDevices 
        else state.value.allDevices.filter { it.name.contains(query, ignoreCase = true) || it.id.contains(query) }
        updateState { it.copy(filteredDevices = filtered) }
    }
}
