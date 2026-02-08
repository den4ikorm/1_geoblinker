package org.example.geoblinker.presentation.features.devices

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import org.example.geoblinker.data.device.DeviceRepository
import org.example.geoblinker.data.device.DeviceResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DevicesUiState(
    val isLoading: Boolean = false,
    val devices: List<DeviceResponse> = emptyList(),
    val error: String? = null
)

class DevicesViewModel(
    private val repository: DeviceRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        screenModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching {
                repository.getDeviceList()
            }.onSuccess { list ->
                _uiState.value = DevicesUiState(isLoading = false, devices = list)
            }.onFailure { e ->
                _uiState.value = DevicesUiState(isLoading = false, error = e.message)
            }
        }
    }
}
