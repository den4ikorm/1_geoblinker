package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import kotlinx.coroutines.launch

class DeviceDetailsViewModel(
    private val repository: DeviceRepository
) : BaseViewModel<DetailsState, DetailsEvent, DetailsEffect>(DetailsState()) {

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.LoadDetails -> loadDeviceData(event.deviceId)
            DetailsEvent.OnRefreshClick -> {
                state.value.device?.id?.let { loadDeviceData(it) }
            }
            DetailsEvent.OnBackClick -> sendEffect(DetailsEffect.NavigateBack)
        }
    }

    private fun loadDeviceData(deviceId: String) {
        updateState { it.copy(isLoading = 1L) }
        scope.launch {
            try {
                // Ищем устройство в репозитории
                val raw = repository.getDevices().find { it.id == deviceId }
                if (raw != null) {
                    val uiDevice = DeviceDetailsUi(
                        id = raw.id,
                        name = raw.name,
                        lastUpdate = "Сегодня, 12:45", // Заглушка времени
                        lat = LegacyConverter.convertCoordinate(raw.lat),
                        lng = LegacyConverter.convertCoordinate(raw.lng),
                        speed = LegacyConverter.convertSpeed(raw.speed),
                        fuelLevel = 75, // Мок-данные телеметрии
                        batteryVoltage = 12.6,
                        isIgnitionOn = raw.status == "online",
                        dailyMileage = 145.2,
                        address = "ул. Ленина, Пойковский"
                    )
                    updateState { it.copy(device = uiDevice, isLoading = 0L) }
                } else {
                    updateState { it.copy(isLoading = 0L, error = "Устройство не найдено") }
                }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = 0L, error = e.message) }
            }
        }
    }
}
