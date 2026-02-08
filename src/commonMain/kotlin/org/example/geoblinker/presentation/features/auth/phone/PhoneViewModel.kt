package org.example.geoblinker.presentation.features.auth.phone

import org.example.geoblinker.core.platform.PlatformViewModel
import kotlinx.coroutines.flow.*

class PhoneViewModel : PlatformViewModel() {
    private val _state = MutableStateFlow(PhoneState())
    val state = _state.asStateFlow()

    fun onEvent(event: PhoneEvent) {
        when (event) {
            is PhoneEvent.OnCharEntered -> {
                if (_state.value.phoneNumber.length < 10) {
                    val newRaw = _state.value.phoneNumber + event.char
                    updateState(newRaw)
                }
            }
            is PhoneEvent.OnDeleteChar -> {
                val newRaw = _state.value.phoneNumber.dropLast(1)
                updateState(newRaw)
            }
            is PhoneEvent.OnSubmit -> { /* Логика входа */ }
        }
    }

    private fun updateState(raw: String) {
        val formatted = StringBuilder("+7 ")
        raw.forEachIndexed { index, c ->
            when (index) {
                3, 6, 8 -> formatted.append(" $c")
                else -> formatted.append(c)
            }
        }
        _state.update { it.copy(phoneNumber = raw, formattedNumber = formatted.toString()) }
    }
}
