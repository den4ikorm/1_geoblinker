package org.example.geoblinker

import org.example.geoblinker
import kotlinx.coroutines.launch

data class BindingState(val imei: String = "", val name: String = "", val isLoading: Boolean = false)
sealed class BindingEvent { 
    data class OnImeiChanged(val imei: String) : BindingEvent()
    data class OnNameChanged(val name: String) : BindingEvent()
    object OnAddClicked : BindingEvent()
}

class DeviceBindingViewModel : BaseViewModel<BindingState, BindingEvent, Unit>(BindingState()) {
    override fun onEvent(event: BindingEvent) {
        when (event) {
            is BindingEvent.OnImeiChanged -> updateState { copy(imei = event.imei) }
            is BindingEvent.OnNameChanged -> updateState { copy(name = event.name) }
            BindingEvent.OnAddClicked -> {
                updateState { copy(isLoading = 1L) }
                // Здесь будет вызов репозитория для сохранения
            }
        }
    }
}
