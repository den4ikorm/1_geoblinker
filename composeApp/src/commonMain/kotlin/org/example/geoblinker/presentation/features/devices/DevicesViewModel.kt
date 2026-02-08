package org.example.geoblinker.presentation.features.devices

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.geoblinker.domain.repository.DeviceRepository

class DevicesViewModel(private val repository: DeviceRepository) : ViewModel() {
    private val _devices = MutableStateFlow<List<Any>>(emptyList()) // Позже заменим Any на модель Device
    val devices: StateFlow<List<Any>> = _devices

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadDevices() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Здесь будет запрос к ibronevik.ru / gps666.net
                // Используем наш репозиторий
                val result = repository.fetchDevices()
                _devices.value = result
            } catch (e: Exception) {
                // Ошибка обработки
            } finally {
                _isLoading.value = false
            }
        }
    }
}
