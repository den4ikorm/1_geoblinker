package org.example.geoblinker

import org.example.geoblinker

class PhoneViewModel : BaseViewModel<PhoneState, PhoneEvent>(PhoneState()) {

    override fun onEvent(event: PhoneEvent) {
        when (event) {
            is PhoneEvent.OnPhoneInput -> {
                val isValid = event.number.length >= 10
                updateState { it.copy(phoneNumber = event.number, isPhoneValid = isValid, error = null) }
            }
            is PhoneEvent.OnSubmitClick -> {
                if (state.value.isPhoneValid) {
                    updateState { it.copy(isLoading = 1L) }
                    // Здесь будет переход на ввод кода
                } else {
                    updateState { it.copy(error = "Введите корректный номер") }
                }
            }
        }
    }
}
