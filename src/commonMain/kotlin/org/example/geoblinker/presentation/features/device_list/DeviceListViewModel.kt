package org.example.geoblinker.presentation.features.device_list

import org.example.geoblinker.domain.repository.DeviceRepository
import org.example.geoblinker.presentation.core.BaseViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DeviceListViewModel(
    private val repository: DeviceRepository
) : BaseViewModel<DeviceListState, DeviceListEvent>(DeviceListState()) {

    init { onEvent(DeviceListEvent.LoadDevices) }

    override fun onEvent(event: DeviceListEvent) {
        when (event) {
            is DeviceListEvent.LoadDevices -> loadDevices()
            is DeviceListEvent.OnDeviceClick -> { /* Навигация будет тут */ }
        }
    }

    private fun loadDevices() {
        updateState { it.copy(isLoading = true) }
        MainScope().launch {
            repository.getDevices().onSuccess { list ->
                updateState { it.copy(devices = list, isLoading = false) }
            }
        }
    }
}
