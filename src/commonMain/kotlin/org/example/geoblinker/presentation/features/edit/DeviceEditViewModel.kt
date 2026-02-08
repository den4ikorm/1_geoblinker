package org.example.geoblinker.presentation.features.edit

import org.example.geoblinker.core.base.BaseViewModel
import org.example.geoblinker.domain.repository.DeviceRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class DeviceEditViewModel(
    private val repository: DeviceRepository
) : BaseViewModel<DeviceEditState, DeviceEditEvent, DeviceEditEffect>(DeviceEditState()) {

    override fun onEvent(event: DeviceEditEvent) {
        when (event) {
            is DeviceEditEvent.OnLoadDevice -> {
                updateState { copy(imei = event.imei, deviceName = "Загрузка...") }
            }
            is DeviceEditEvent.OnNameChanged -> {
                val isValid = event.name.length >= 2
                updateState { copy(deviceName = event.name, isNameValid = isValid) }
            }
            is DeviceEditEvent.OnIconSelected -> {
                updateState { copy(selectedIconType = event.iconType) }
            }
            is DeviceEditEvent.OnSaveClick -> save()
        }
    }

    private fun save() {
        updateState { copy(isSaving = true) }
        scope.launch {
            delay(1000) // Имитация сохранения
            setEffect { DeviceEditEffect.ShowToast("Сохранено") }
            setEffect { DeviceEditEffect.NavigateBack }
        }
    }
}
