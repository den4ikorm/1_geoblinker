package org.example.geoblinker.presentation.features.devicelist

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*
import org.example.geoblinker.domain.repository.DeviceRepository

class DeviceListViewModel(private val repository: DeviceRepository) : ViewModel() {
    private val _state = MutableStateFlow(DeviceListState())
    val state = _state.asStateFlow()

    init {
        loadDevices()
    }

    private fun loadDevices() {
        _state.update { it.copy(isLoading = 1) }
        // В будущем здесь будет реальный маппинг из репозитория
        // с использованием LegacyConverter для координат и скорости
        _state.update { it.copy(isLoading = 0) }
    }
}
